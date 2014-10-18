CREATE TABLE Card
(
	id INT PRIMARY KEY AUTO_INCREMENT,
        player_id INT,
        game_id INT,
        time TIME, 
        kind CHAR(250),
        comment TEXT,
        owner_id INT,
        swap_id INT,
        faul_id INT
)