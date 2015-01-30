;; migrations/20150108160545457-create-users-table.clj

(defn up []
  ["CREATE TABLE users(
     id SERIAL PRIMARY KEY,
     email VARCHAR (355) UNIQUE NOT NULL,
     password VARCHAR (60) NOT NULL,
     role VARCHAR (50) NOT NULL)"])

(defn down []
  ["DROP TABLE users CASCADE"])

