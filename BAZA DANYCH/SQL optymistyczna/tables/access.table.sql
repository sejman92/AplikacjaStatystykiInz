CREATE TABLE Access
(
	id INT NOT NULL AUTO_INCREMENT,
        user_id INT NOT NULL,
        team_id INT NOT NULL,
        PRIMARY KEY(id),
        FOREIGN KEY(user_id) REFERENCES User(id),
        FOREIGN KEY(team_id) REFERENCES Team(id)
)