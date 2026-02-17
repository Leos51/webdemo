USE springboot;

-- Création de la table
CREATE TABLE IF NOT EXISTS `user` (
                                      id INT AUTO_INCREMENT PRIMARY KEY,
                                      firstname VARCHAR(255) NOT NULL,
    lastname VARCHAR(255) NOT NULL
    ) ENGINE=InnoDB;

-- Données de test
INSERT INTO `user` (firstname, lastname) VALUES
                                             ('Alice', 'THORNTON'),
                                             ('Lance', 'RIDDLE'),
                                             ('Brandon', 'HUTTINS'),
                                             ('Bob', 'MARTIN');

SELECT 'Database initialized with test data!' AS Status;
