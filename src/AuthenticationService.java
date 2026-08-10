import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class AuthenticationService {

    private final Map<String, String> users =
            new ConcurrentHashMap<>();

    private final Map<String, Boolean> loginStatus =
            new ConcurrentHashMap<>();

    public boolean registerUser(
            String username,
            String password) {

        if (username == null || password == null) {

            System.out.println(
                    "Username or password cannot be null"
            );

            return false;
        }

        String oldPassword =
                users.putIfAbsent(username, password);

        if (oldPassword != null) {

            System.out.println(
                    "User already exists: "
                            + username
            );

            return false;
        }

        loginStatus.put(username, false);

        System.out.println(
                "User registered successfully: "
                        + username
        );

        return true;
    }
    public synchronized boolean login(
            String username,
            String password) {

        if (!users.containsKey(username)) {

            System.out.println(
                    "User does not exist: "
                            + username
            );

            return false;
        }

        String storedPassword =
                users.get(username);

        if (storedPassword.equals(password)) {

            loginStatus.put(username, true);

            System.out.println(
                    Thread.currentThread().getName()
                            + " -> "
                            + username
                            + " logged in successfully"
            );

            return true;
        }

        System.out.println(
                "Invalid password for "
                        + username
        );

        return false;
    }

    public synchronized void logout(
            String username) {

        if (loginStatus.containsKey(username)) {

            loginStatus.put(username, false);

            System.out.println(
                    username
                            + " logged out successfully"
            );
        }
    }
    public boolean isLoggedIn(
            String username) {

        return loginStatus.getOrDefault(
                username,
                false
        );
    }
    public boolean authenticate(
            String username,
            String password) {

        return users.containsKey(username)
                && users.get(username).equals(password);
    }
    public int getUserCount() {

        return users.size();
    }

}
