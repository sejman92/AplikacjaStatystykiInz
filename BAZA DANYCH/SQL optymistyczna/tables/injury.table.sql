CREATE TABLE Injury
(
	id INT PRIMARY KEY AUTO_INCREMENT,
        player_injured_id INT,
        game_id INT,
        time TIME, 
        duration INT,
        kind CHAR(250),
        comment TEXT,
        owner_id INT,
        swap_id INT
)