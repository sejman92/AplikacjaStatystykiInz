CREATE TABLE Defense
(
	id INT PRIMARY KEY AUTO_INCREMENT,
        player_id INT,
        game_id INT,
        time TIME,
		owner_id INT,
        comment TEXT        
)