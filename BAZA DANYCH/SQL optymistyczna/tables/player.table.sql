CREATE TABLE player
(
	id INT NOT NULL AUTO_INCREMENT,
        name CHAR(20),
        surname CHAR(30),
        no INT,
        role CHAR(20),
        owner_id INT NOT NULL,
        team_id INT NOT NULL,
        PRIMARY KEY (id),
        FOREIGN KEY(owner_id) REFERENCES user(id),
        FOREIGN KEY(team_id) REFERENCES team(id)
)