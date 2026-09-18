package com.chatapp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Login class.
 * Test data and expected results are taken directly from the PoE brief's
 * "unit tests" table, so these should be used to mark the task.
 */
public class LoginTest {

    // --- Username format tests ---

    @Test
    void testUserNameCorrectlyFormatted() {
        Login login = new Login("kyl_1", "Ch&&sec@ke99!", "+27838968976", "Kyle", "Smith");
        assertTrue(login.checkUserName());
    }

    @Test
    void testUserNameIncorrectlyFormatted() {
        Login login = new Login("kyle!!!!!!", "Ch&&sec@ke99!", "+27838968976", "Kyle", "Smith");
        assertFalse(login.checkUserName());
    }

    // --- Password complexity tests ---

    @Test
    void testPasswordMeetsComplexity() {
        Login login = new Login("kyl_1", "Ch&&sec@ke99!", "+27838968976", "Kyle", "Smith");
        assertTrue(login.checkPasswordComplexity());
    }

    @Test
    void testPasswordDoesNotMeetComplexity() {
        Login login = new Login("kyl_1", "password", "+27838968976", "Kyle", "Smith");
        assertFalse(login.checkPasswordComplexity());
    }

    // --- Cell phone number tests ---

    @Test
    void testCellPhoneCorrectlyFormatted() {
        Login login = new Login("kyl_1", "Ch&&sec@ke99!", "+27838968976", "Kyle", "Smith");
        assertTrue(login.checkCellPhoneNumber());
    }

    @Test
    void testCellPhoneIncorrectlyFormatted() {
        Login login = new Login("kyl_1", "Ch&&sec@ke99!", "08966553", "Kyle", "Smith");
        assertFalse(login.checkCellPhoneNumber());
    }

    // --- Registration message tests ---

    @Test
    void testRegisterUserWelcomeMessage() {
        Login login = new Login("kyl_1", "Ch&&sec@ke99!", "+27838968976", "Kyle", "Smith");
        String result = login.registerUser();
        assertTrue(result.contains("successfully registered"));
    }

    // --- Login success/failure tests ---

    @Test
    void testLoginSuccessful() {
        // Register the account first
        Login registration = new Login("kyl_1", "Ch&&sec@ke99!", "+27838968976", "Kyle", "Smith");
        registration.registerUser();

        // Now attempt to log in with the same credentials
        Login loginAttempt = new Login("kyl_1", "Ch&&sec@ke99!");
        assertEquals(true, loginAttempt.loginUser());
    }

    @Test
    void testLoginFailed() {
        // Register the account first
        Login registration = new Login("kyl_1", "Ch&&sec@ke99!", "+27838968976", "Kyle", "Smith");
        registration.registerUser();

        // Attempt to log in with the wrong password
        Login loginAttempt = new Login("kyl_1", "wrongPassword1!");
        assertEquals(false, loginAttempt.loginUser());
    }

    // --- Boolean check convenience tests (as listed in the brief) ---

    @Test
    void testUserNameCorrectlyFormattedReturnsTrue() {
        Login login = new Login("kyl_1", "Ch&&sec@ke99!", "+27838968976", "Kyle", "Smith");
        assertEquals(true, login.checkUserName());
    }

    @Test
    void testUserNameIncorrectlyFormattedReturnsFalse() {
        Login login = new Login("kyle!!!!!!", "Ch&&sec@ke99!", "+27838968976", "Kyle", "Smith");
        assertEquals(false, login.checkUserName());
    }

    @Test
    void testPasswordMeetsComplexityReturnsTrue() {
        Login login = new Login("kyl_1", "Ch&&sec@ke99!", "+27838968976", "Kyle", "Smith");
        assertEquals(true, login.checkPasswordComplexity());
    }

    @Test
    void testPasswordDoesNotMeetComplexityReturnsFalse() {
        Login login = new Login("kyl_1", "password", "+27838968976", "Kyle", "Smith");
        assertEquals(false, login.checkPasswordComplexity());
    }

    @Test
    void testCellPhoneCorrectlyFormattedReturnsTrue() {
        Login login = new Login("kyl_1", "Ch&&sec@ke99!", "+27838968976", "Kyle", "Smith");
        assertEquals(true, login.checkCellPhoneNumber());
    }

    @Test
    void testCellPhoneIncorrectlyFormattedReturnsFalse() {
        Login login = new Login("kyl_1", "Ch&&sec@ke99!", "08966553", "Kyle", "Smith");
        assertEquals(false, login.checkCellPhoneNumber());
    }
}
