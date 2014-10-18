CREATE TABLE Passing
(
	id INT PRIMARY KEY AUTO_INCREMENT,
        player_passing_id INT,
        player_getting_id INT,
        game_id INT,
        time TIME,
        comment TEXT,
		owner_id INT,
        successful BOOL        
)