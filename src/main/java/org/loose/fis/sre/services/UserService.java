package org.loose.fis.sre.services;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.sre.exceptions.UsernameAlreadyExistsException;
import org.loose.fis.sre.model.User;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

import static org.loose.fis.sre.services.FileSystemService.getPathToFile;

public class UserService {

    private static ObjectRepository<User> userRepository;

    public static void initDatabase() {
        Nitrite database = Nitrite.builder()
                .filePath(getPathToFile("users-database.db").toFile())
                .openOrCreate("test", "test");

        userRepository = database.getRepository(User.class);
    }

    public static void addUser(String username, String password, String role) throws UsernameAlreadyExistsException {
        checkUserDoesNotAlreadyExist(username);
        userRepository.insert(new User(username, encodePassword(username, password), role));
    }

    private static void checkUserDoesNotAlreadyExist(String username) throws UsernameAlreadyExistsException {
        for (User user : userRepository.find()) {
            if (Objects.equals(username, user.getUsername()))
                throw new UsernameAlreadyExistsException(username);
        }
    }

    private static String encodePassword(String salt, String password) {
        MessageDigest md = getMessageDigest();
        md.update(salt.getBytes(StandardCharsets.UTF_8));

        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));

        // This is the way a password should be encoded when checking the credentials
        return new String(hashedPassword, StandardCharsets.UTF_8)
                .replace("\"", ""); //to be able to save in JSON format
    }

    private static MessageDigest getMessageDigest() {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-512 does not exist!");
        }
        return md;
    }


    public static String validateLogin(String username, String password) {
        for (User user : userRepository.find()) {
            if(username.equals(user.getUsername()))
            {   String encodedPassword=encodePassword(username,password);
                if (encodedPassword.equals(user.getPassword())) {
                    return "Valid";
                }
            }
        }
        return "Invalid";
    }


    public static boolean modifyClientAccountInfo (String username, String password, String email, String firstName, String lastName, String phoneNumber,String stadiumAdress,String capacity,String stadiumName,String ownerName) {
        for (User user : userRepository.find()) {
            if(username.equals(user.getUsername()))
            {   String encodedPassword=encodePassword(username,password);
                if (encodedPassword.equals(user.getPassword())) {
                    user.setEmail(email);
                    user.setFirstName(firstName);
                    user.setLastName(lastName);
                    user.setPhoneNumber(phoneNumber);
                    user.setStadiumAdress(stadiumAdress);
                    user.setCapacity(capacity);
                    user.setStadiumName(stadiumName);
                    user.setOwnerName(ownerName);
                    userRepository.update(user);
                    return true;
                }
            }
        }
        return false;
    }

    public static User returnCurrentUser (String username, String password){
        for (User user : userRepository.find()) {
            if(username.equals(user.getUsername()))
            {   String encodedPassword=encodePassword(username,password);
                if (encodedPassword.equals(user.getPassword())) {
                    return user;
                }
            }
        }
        return null;
    }

}
