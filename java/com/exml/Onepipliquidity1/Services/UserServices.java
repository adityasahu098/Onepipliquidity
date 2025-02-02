package com.exml.Onepipliquidity1.Services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.exml.Onepipliquidity1.model.ResponseDTO;
import com.exml.Onepipliquidity1.model.User_model;
import com.exml.Onepipliquidity1.repository.UserRepository;

import java.util.Optional;


@Service
public class UserServices {

    public static final Logger logger = LoggerFactory.getLogger(UserServices.class);

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JavaMailSender emailSender;


  
   

    // Method to add a new user
    public User_model addUser(User_model newUser) {
        try {
            // Check if a user with the same email already exists
            Optional<User_model> existingUser = userRepository.findByEmail(newUser.getEmail());
            if (existingUser.isPresent()) {
                logger.info("User with email {} already exists", newUser.getEmail());
                throw new IllegalArgumentException("User with this email already exists!");
            }

            // Save the new user
            logger.info("Saving new user with email {}", newUser.getEmail());
            return userRepository.save(newUser);

        } catch (IllegalArgumentException e) {
            logger.error("Error while adding user: {}", e.getMessage());
            throw e;  // Re-throw to handle in the controller
        } catch (Exception ex) {
            logger.error("Unexpected error occurred while adding user: ", ex);
            throw new RuntimeException("Error while adding User", ex);
        }
    }

    // Optional: Save user (use only if needed)
    public User_model saveUser(User_model newUser) {
        try {
            logger.info("Saving user with email {}", newUser.getEmail());
            return userRepository.save(newUser);
        } catch (Exception ex) {
            logger.error("Error while saving user: ", ex);
            throw new RuntimeException("Failed to save user", ex);
        }
    }
    
    
    public ResponseEntity<?> loginUser(User_model loginRequest) {
        try {
            // Fetch user by email
            Optional<User_model> optionalUser = userRepository.findByEmail(loginRequest.getEmail());
            System.out.println("Step 1: User email received: " + loginRequest.getEmail());
            System.out.println("Step 10: irshaad: " + optionalUser);

            // Check if user exists
            if (optionalUser.isEmpty()) {
                System.out.println("Step 2: Invalid email (user not found)");
                String msg = "Invalid email or password";
                return new ResponseEntity<>(msg, HttpStatus.UNAUTHORIZED); // Return 401 Unauthorized
            }

            User_model user = optionalUser.get();
            System.out.println("Step 3: User found, checking password...");

            // Check if password matches
            if (!user.getPassword().equals(loginRequest.getPassword())) {
                System.out.println("Step 4: Invalid password");
                String msg = "Invalid email or password";
                return new ResponseEntity<>(msg, HttpStatus.UNAUTHORIZED); // Return 401 Unauthorized
            }

            // If the login is successful
            System.out.println("Step 5: Login successful");
            return new ResponseEntity<>("Login successful", HttpStatus.OK); // Return success message

        } catch (Exception ex) {
            // Handle any exceptions
            System.out.println("Step 6: Error occurred during login");
            return new ResponseEntity<>("Error logging in", HttpStatus.INTERNAL_SERVER_ERROR); // Return 500 Internal Server Error
        }
    }
    
    

    
    public ResponseDTO forgotPassword(String email) {
        try {
            System.out.println("Checking email: " + email);

            // Trim and normalize email
            email = email.trim().toLowerCase();

            // Check if the email exists in the database
            Optional<User_model> userOptional = userRepository.findByEmail(email);

            if (userOptional.isEmpty()) {
                System.out.println("User not found: " + email);
                return new ResponseDTO(HttpStatus.NOT_FOUND.value(), "User with this email does not exist.");
            }

            // Get user details
            User_model user = userOptional.get();
            String resetToken = generateResetToken(); // Generate a fake token (should be replaced with actual logic)

            // Send reset email
            sendResetEmail(user.getEmail(), resetToken);

            return new ResponseDTO(HttpStatus.OK.value(), "Password reset link has been sent to your email.", user.getOnepipliquidityModelId());

        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error processing forgot password request");
        }
    }

    /**
     * Generates a random password reset token
     */
    private String generateResetToken() {
        return java.util.UUID.randomUUID().toString();
    }

    /**
     * Sends a password reset email to the user
     */
    private void sendResetEmail(String email, String token) {
        try {
            String resetLink = "http://localhost:8080/reset-password?token=" + token;

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setSubject("Password Reset Request");
            message.setText("Click the link to reset your password: " + resetLink);

            emailSender.send(message);  // <-- Change 'mailSender' to 'emailSender'

            System.out.println("Password reset email sent to: " + email);
        } catch (Exception e) {
            System.err.println("Error sending email: " + e.getMessage());
        }
    }

    
    
    
    
    
     
}
