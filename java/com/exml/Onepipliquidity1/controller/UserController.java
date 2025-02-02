 package com.exml.Onepipliquidity1.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.exml.Onepipliquidity1.model.Bonus;
import com.exml.Onepipliquidity1.model.EmailRequest;
import com.exml.Onepipliquidity1.model.ResponseDTO;
import com.exml.Onepipliquidity1.model.ResponseDTO1;
import com.exml.Onepipliquidity1.model.User_model;
import com.exml.Onepipliquidity1.repository.BonusRepository;
import com.exml.Onepipliquidity1.repository.UserRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")

public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRepository userRepository;
    
    
    @Autowired
    private BonusRepository bonusRepository;

    @PostMapping("/addUser")
    public ResponseEntity<ResponseDTO> addUser(@RequestBody User_model user) {
        try {
            String email = user.getEmail();
            Optional<User_model> existingUser = userRepository.findByEmail(email);

            if (existingUser.isPresent()) {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(new ResponseDTO(HttpStatus.BAD_REQUEST.value(), "User with email already exists!"));
            }

            // Save the new user
            userRepository.save(user);
            
            // Get the generated ID
            int generatedId = user.getOnepipliquidityModelId();
            
            // Return the response with the generated ID
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDTO(HttpStatus.OK.value(), "User added successfully with ID: " + generatedId));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), "An addUser error occurred: " + ex.getMessage()));
        }
    }



 
    
    @PostMapping("/loginUser")
    public ResponseEntity<ResponseDTO> loginUser(@RequestBody User_model loginRequest) {
        try {
            // Fetch user by email
            Optional<User_model> optionalUser = userRepository.findByEmail(loginRequest.getEmail());

            // Check if user exists
            if (optionalUser.isEmpty()) {
                // User not found
                ResponseDTO response = new ResponseDTO(HttpStatus.UNAUTHORIZED.value(), "Invalid email or password");
                return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED); // Return 401 Unauthorized
            }

            User_model user = optionalUser.get();

            // Check if password matches
            if (!user.getPassword().equals(loginRequest.getPassword())) {
                // Password doesn't match
                ResponseDTO response = new ResponseDTO(HttpStatus.UNAUTHORIZED.value(), "Invalid email or password");
                return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED); // Return 401 Unauthorized
            }

            // Login successful
            ResponseDTO response = new ResponseDTO(HttpStatus.OK.value(), "Login successful", user.getOnepipliquidityModelId());
            return new ResponseEntity<>(response, HttpStatus.OK); // Return 200 OK

        } catch (Exception ex) {
            // Handle any exceptions
            ResponseDTO response = new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error logging in");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR); // Return 500 Internal Server Error
        }
    }



    
    
    // Forgot Password API
    @PostMapping("/forgotPassword")
    public ResponseEntity<ResponseDTO> forgotPassword(@RequestBody EmailRequest emailRequest) {
        try {
            System.out.println("Received email: " + emailRequest.getEmail());  // Debugging line

            // Trim and normalize email
            String email = emailRequest.getEmail().trim().toLowerCase();

            // Check if the email exists in the database
            Optional<User_model> userOptional = userRepository.findByEmail(email);

            if (userOptional.isEmpty()) {
                return new ResponseEntity<>(new ResponseDTO(HttpStatus.NOT_FOUND.value(), "User with this email does not exist.", null), HttpStatus.NOT_FOUND);
            }

            // If the user is found, send response
            User_model user = userOptional.get();
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(), "Password reset link has been sent to your email.", user.getOnepipliquidityModelId()), HttpStatus.OK);

        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error processing forgot password request", null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    
  
    
    
 // Change Password API
    @PostMapping("/changePassword")
    public ResponseEntity<ResponseDTO> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest) {
        try {
            // Validate the current password
            Optional<User_model> userOptional = userRepository.findByEmail(changePasswordRequest.getEmail());

            if (userOptional.isEmpty()) {
                logger.info("User with email {} not found.", changePasswordRequest.getEmail());
                return new ResponseEntity<>(new ResponseDTO(404, "User with this email does not exist."), HttpStatus.NOT_FOUND);
            }

            User_model user = userOptional.get();

            // Check if the current password matches
            if (!user.getPassword().equals(changePasswordRequest.getCurrentPassword())) {
                return new ResponseEntity<>(new ResponseDTO(401, "Invalid current password."), HttpStatus.UNAUTHORIZED);
            }

            // Update the password
            user.setPassword(changePasswordRequest.getNewPassword());
            userRepository.save(user);

            return new ResponseEntity<>(new ResponseDTO(200, "Password changed successfully."), HttpStatus.OK);

        } catch (Exception ex) {
            logger.error("Error occurred while changing password for user: ", ex);
            return new ResponseEntity<>(new ResponseDTO(500, "Error changing password"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    
    
    
    
    
   
    @PostMapping("/addBonus")
    public ResponseEntity<ResponseDTO> addBonus(@RequestBody BonusRequest bonusRequest) {
        try {
            logger.info("Received request: Email={}, Amount={}, Description={}", 
                        bonusRequest.getEmail(), bonusRequest.getAmount(), bonusRequest.getDescription());

            Optional<User_model> optionalUser = userRepository.findByEmailIgnoreCase(bonusRequest.getEmail());

            if (optionalUser.isEmpty()) {
                logger.info("User with email {} not found.", bonusRequest.getEmail());
                return new ResponseEntity<>(new ResponseDTO(404, "User with this email does not exist."), HttpStatus.NOT_FOUND);
            }

            User_model user = optionalUser.get();
            logger.info("User found: ID={}, Email={}", user.getOnepipliquidityModelId(), user.getEmail());

            Bonus bonus = new Bonus();
            bonus.setUser(user);

            // Convert the double value to BigDecimal
            BigDecimal amount = BigDecimal.valueOf(bonusRequest.getAmount());
            bonus.setAmount(amount);

            bonus.setDescription(bonusRequest.getDescription());

            logger.info("Saving bonus for user ID: {}", user.getOnepipliquidityModelId());
            bonusRepository.save(bonus);

            logger.info("Bonus added successfully for user: {}", bonusRequest.getEmail());
            return new ResponseEntity<>(new ResponseDTO(200, "Bonus added successfully."), HttpStatus.OK);

        } catch (Exception ex) {
            logger.error("Error occurred while adding bonus: ", ex);
            return new ResponseEntity<>(new ResponseDTO(500, "Error adding bonus"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }






  
    @GetMapping("/getBonus")
    public ResponseEntity<ResponseDTO1> getBonus(@RequestParam String email) {
        try {
            logger.info("Received request to fetch bonus details for email={}", email);

            Optional<User_model> optionalUser = userRepository.findByEmailIgnoreCase(email);

            if (optionalUser.isEmpty()) {
                logger.info("User with email {} not found.", email);
                return new ResponseEntity<>(new ResponseDTO1(404, "User with this email does not exist."), HttpStatus.NOT_FOUND);
            }

            User_model user = optionalUser.get();
            logger.info("User found: ID={}, Email={}", user.getOnepipliquidityModelId(), user.getEmail());

            List<Bonus> bonuses = bonusRepository.findByUser(user);

            if (bonuses.isEmpty()) {
                logger.info("No bonuses found for user with email {}", email);
                return new ResponseEntity<>(new ResponseDTO1(404, "No bonuses found for this user."), HttpStatus.NOT_FOUND);
            }

            logger.info("Bonus details fetched successfully for user: {}", email);
            return new ResponseEntity<>(new ResponseDTO1(200, "Bonus details retrieved successfully.", bonuses), HttpStatus.OK);

        } catch (Exception ex) {
            logger.error("Error occurred while fetching bonus details: ", ex);
            return new ResponseEntity<>(new ResponseDTO1(500, "Error fetching bonus details"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    
    
    
    
    
    // Inner class to represent the password change request
    public static class ChangePasswordRequest {
        private String email;
        private String currentPassword;
        private String newPassword;

        // Getters and Setters
        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getCurrentPassword() {
            return currentPassword;
        }

        public void setCurrentPassword(String currentPassword) {
            this.currentPassword = currentPassword;
        }

        public String getNewPassword() {
            return newPassword;
        }

        public void setNewPassword(String newPassword) {
            this.newPassword = newPassword;
        }
    }
    
    
   
    
    public static class BonusRequest {
        private String email;
        private double amount;
        private String description;

        // Getters and Setters
        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
    
    
    
    
    
    
}
