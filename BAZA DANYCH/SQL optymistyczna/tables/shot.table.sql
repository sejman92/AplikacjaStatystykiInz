CREATE TABLE Shot
(
	id INT PRIMARY KEY AUTO_INCREMENT,
        player_id INT,
        game_id INT,
        comment TEXT,
        palce CHAR(50),
		success BOOL,
        owner_id INT        
)