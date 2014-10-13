CREATE PROCEDURE add_team 
@name VARCHAR(200)
AS
BEGIN
INSERT INTO druzyna (nazwa) VALUES (@name);
END;