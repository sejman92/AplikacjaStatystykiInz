CREATE TABLE Swap
(
	id INT PRIMARY KEY AUTO_INCREMENT,
        player_in_id INT,
        player_out_id INT,
        game_id INT,
        comment TEXT,
        injury_id INT,
        owner_id INT        
)