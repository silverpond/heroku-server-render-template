;; migrations/20150129151956512-create-admin-user.clj

; Password is "password"
(defn up []
  ["INSERT INTO users(email, password, role) VALUES ('admin@example.com', '$2a$10$CDeKFYCzzsjy6tTdE8M/WuMzn8iO0htuof4.RkGk6YIBvSk0KpwS2', 'admin')"
   "INSERT INTO users(email, password, role) VALUES ('user@example.com', '$2a$10$CDeKFYCzzsjy6tTdE8M/WuMzn8iO0htuof4.RkGk6YIBvSk0KpwS2', 'user')"])

(defn down []
  ["DELETE FROM users WHERE email='admin@example.com'"
   "DELETE FROM users WHERE email='user@example.com'"])
