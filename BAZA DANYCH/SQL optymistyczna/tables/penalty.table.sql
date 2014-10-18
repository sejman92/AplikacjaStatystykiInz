CREATE TABLE Penalty
(
	id INT PRIMARY KEY AUTO_INCREMENT,
        player_id INT,
        game_id INT,
        time TIME, 
        shot_id INT,
        comment TEXT,
        owner_id INT
)