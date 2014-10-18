CREATE TABLE Takeover
(
	id INT PRIMARY KEY AUTO_INCREMENT,
        player_id INT,
        game_id INT,
        time TIME,
        comment TEXT,
        owner_id INT
)