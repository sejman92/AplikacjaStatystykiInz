CREATE PROCEDURE sp_delete_user(IN id INT)
BEGIN

DELETE FROM Access WHERE user_id=id;
DELETE FROM User WHERE User.id=id;

END

GRANT EXECUTE sp_delete_user TO ALL 