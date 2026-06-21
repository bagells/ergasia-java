package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.mindrot.jbcrypt.BCrypt;

public class SystemDao {

	private Connection connection;

	public SystemDao() {
        connection = utilities.DbUtil.getConnection();
    }

	/**
	 * Hash a password using BCrypt
	 * @param plainTextPassword The plain text password to hash
	 * @return The BCrypt hashed password
	 */
	public String hashPassword(String plainTextPassword) {
		// BCrypt automatically generates a salt and includes it in the hash
		// The default log_rounds is 10, which provides good security
		return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt(12));
	}

	/**
	 * Verify a password against a BCrypt hash
	 * @param plainTextPassword The plain text password to verify
	 * @param hashedPassword The BCrypt hash to verify against
	 * @return true if password matches, false otherwise
	 */
	public boolean verifyPassword(String plainTextPassword, String hashedPassword) {
		try {
			return BCrypt.checkpw(plainTextPassword, hashedPassword);
		} catch (IllegalArgumentException e) {
			// Handle cases where the hash is invalid
			return false;
		}
	}

	public String loginusernameCheck(String username) {
		String answer = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM USERS WHERE username=?");
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            
            if (!rs.next()) {
            	answer = "There is no user with the username: " + username + ", please enter a valid username!";
        	} else {
            	answer = username;
        	}
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return answer;
    }

	/**
	 * Check if account is locked
	 * @param username The username to check
	 * @return "locked" if locked, "unlocked" if not locked, or error message
	 */
	public String checkAccountLockStatus(String username) {
		String status = null;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(
				"SELECT account_locked, locked_until FROM USERS WHERE username=?"
			);
			preparedStatement.setString(1, username);
			ResultSet rs = preparedStatement.executeQuery();
			
			if (rs.next()) {
				boolean isLocked = rs.getBoolean("account_locked");
				java.sql.Timestamp lockedUntil = rs.getTimestamp("locked_until");
				
				if (isLocked) {
					// Check if temporary lock has expired
					if (lockedUntil != null && lockedUntil.before(new java.sql.Timestamp(System.currentTimeMillis()))) {
						// Lock expired, automatically unlock the account
						unlockAccount(username);
						status = "unlocked";
					} else {
						status = "locked";
					}
				} else {
					status = "unlocked";
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			status = "error";
		}
		return status;
	}

	/**
	 * Increment failed login attempts and lock account if threshold is reached
	 * @param username The username
	 */
	public void incrementFailedAttempts(String username) {
		try {
			// Get current failed attempts
			PreparedStatement ps1 = connection.prepareStatement(
				"SELECT failed_attempts FROM USERS WHERE username=?"
			);
			ps1.setString(1, username);
			ResultSet rs = ps1.executeQuery();
			
			if (rs.next()) {
				int currentAttempts = rs.getInt("failed_attempts");
				int newAttempts = currentAttempts + 1;
				
				if (newAttempts >= 5) {
					// Lock the account for 15 minutes
					java.sql.Timestamp lockUntil = new java.sql.Timestamp(
						System.currentTimeMillis() + (15 * 60 * 1000) // 15 minutes in milliseconds
					);

					PreparedStatement ps2 = connection.prepareStatement(
						"UPDATE USERS SET failed_attempts=?, account_locked=TRUE, locked_until=? WHERE username=?"
					);
					ps2.setInt(1, newAttempts);
					ps2.setTimestamp(2, lockUntil);
					ps2.setString(3, username);
					ps2.executeUpdate();
					
					System.out.println("Account locked for user: " + username + " until " + lockUntil);
				} else {
					// Just increment the counter
					PreparedStatement ps2 = connection.prepareStatement(
						"UPDATE USERS SET failed_attempts=? WHERE username=?"
					);
					ps2.setInt(1, newAttempts);
					ps2.setString(2, username);
					ps2.executeUpdate();
					
					System.out.println("Failed attempt " + newAttempts + " for user: " + username);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Reset failed attempts counter after successful login
	 * @param username The username
	 */
	public void resetFailedAttempts(String username) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(
				"UPDATE USERS SET failed_attempts=0, account_locked=FALSE, locked_until=NULL WHERE username=?"
			);
			preparedStatement.setString(1, username);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Unlock an account (removes lock and resets attempts)
	 * @param username The username to unlock
	 */
	public void unlockAccount(String username) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(
				"UPDATE USERS SET account_locked=FALSE, failed_attempts=0, locked_until=NULL WHERE username=?"
			);
			preparedStatement.setString(1, username);
			preparedStatement.executeUpdate();
			System.out.println("Account unlocked for user: " + username);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Get the timestamp when account will be unlocked
	 * @param username The username
	 * @return Timestamp when unlocked, or null
	 */
	public java.sql.Timestamp getLockedUntilTime(String username) {
		java.sql.Timestamp lockedUntil = null;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(
				"SELECT locked_until FROM USERS WHERE username=?"
			);
			preparedStatement.setString(1, username);
			ResultSet rs = preparedStatement.executeQuery();
			
			if (rs.next()) {
				lockedUntil = rs.getTimestamp("locked_until");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return lockedUntil;
	}

	/**
	 * Check if the provided password matches the stored password for the user
	 * @param username The username to check
	 * @param plainTextPassword The plain text password entered by user
	 * @return Success message or error message
	 */
	public String passwordCheck(String username, String plainTextPassword) {
		String answer = null;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT password FROM USERS WHERE username=?");
			preparedStatement.setString(1, username);
			ResultSet rs = preparedStatement.executeQuery();
			
			if (rs.next()) {
				String storedHash = rs.getString("password");
				
				// Verify the password using BCrypt
				if (verifyPassword(plainTextPassword, storedHash)) {
					answer = "You logged in!";
				} else {
					answer = "Wrong Password!";
				}
			} else {
				answer = "Wrong Password!";
			}
        } catch (SQLException e) {
            e.printStackTrace();
            answer = "Database error occurred!";
        }
        return answer;
	}

	public String signupusernameCheck(String username) {
		String answer = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM student WHERE username=?");
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            
            if (!rs.next()) {
            	answer = "ok";
        	} else {
            	answer = "There is already a user with the username: " + username + ", please enter a different username.";
        	}
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return answer;
    }

	/**
	 * Register a new user with BCrypt hashed password
	 * Note: The 'salt' parameter is no longer needed as BCrypt handles salt internally
	 */
	public void signup(int id, String username, String name, String surename,
	                   String department, String role, String plainTextPassword) {
		try {
			// Hash the password using BCrypt

			String hashedPassword = hashPassword(plainTextPassword);

			if (role.equals("student")) {
				// Insert into USERS table - no separate salt column needed
				/*PreparedStatement preparedStatement1 = connection.prepareStatement(
					"INSERT INTO student (id,username,name,surename,password,department) VALUES (?,?,?,?,?,?)"
						//εδω στο αρχικό είχε role αλλα εμείς θα το βαλούμε κατευθείαν στο student table
				);
				preparedStatement1.setInt(1, id);
				preparedStatement1.setString(2, username);
				preparedStatement1.setString(3, name);
				preparedStatement1.setString(4, surename);
				preparedStatement1.setString(5, hashedPassword);
				preparedStatement1.setString(6,department);

				preparedStatement1.executeUpdate();
				*/
				// Insert into STUDENTS table
				PreparedStatement preparedStatement = connection.prepareStatement(
					"INSERT INTO student (id,username, name, surename, password,department) VALUES (?,?,?,?,?,?)"
				);
				preparedStatement.setInt(1, id);
				preparedStatement.setString(2, username);
				preparedStatement.setString(3, name);
				preparedStatement.setString(4, surename);
				preparedStatement.setString(5, hashedPassword);
				preparedStatement.setString(6, department);

			//	preparedStatement.setString(6, username);
				preparedStatement.executeUpdate();
				connection.close();
			} else if (role.equals("admin")) {
				// TODO: Implement admin registration
			} else {
				// TODO: Implement other roles
			}
		} catch (SQLException e) {
            e.printStackTrace();
        }
	}

	public String getRole(String username) {
		String role = null;
		try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT role FROM USERS WHERE username=?");
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            
            if (rs.next()) {
            	role = rs.getString("role");
        	}
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return role;
	}
}