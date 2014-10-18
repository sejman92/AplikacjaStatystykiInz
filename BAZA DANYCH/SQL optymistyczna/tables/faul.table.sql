CREATE TABLE Faul
(
	id INT PRIMARY KEY AUTO_INCREMENT,
        game_id INT,
        player_victim_id INT,
        player_ofender_id INT,
        time Time,
        comment TEXT,
        owner_id INT,
        injury_id INT,
        card_id INT
)