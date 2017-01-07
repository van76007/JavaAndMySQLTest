-- Solution for Question 2. Tested on MySQL 5.7
DROP FUNCTION IF EXISTS CAP;

DELIMITER //

-- A function to capitalize the first letter of every words in a string
CREATE FUNCTION CAP(input VARCHAR(255))

RETURNS VARCHAR(255)

DETERMINISTIC

BEGIN
	DECLARE len INT;
	DECLARE i INT;
  DECLARE j INT;
  DECLARE output VARCHAR(255);

	SET len   = CHAR_LENGTH(input);
	SET output = '';
	-- Variable i to mark the position of the SPACE character in the string
	SET i = 1;
  -- Variable j to remember the previous position of the SPACE character
  SET j = 0;

	WHILE (i < len) DO
		IF (MID(input,i,1) = ' ') THEN
			IF (i < len) THEN
        -- Capitalize a word in between two consecutive SPACE characters. Only capitalize the 1st letter
				SET output = CONCAT(output, UPPER(MID(input,j + 1,1)), LOWER(SUBSTRING(input, j + 2, i-j-1)));
        SET j = i;
   
			END IF;
		END IF;
		SET i = i + 1;
	END WHILE;

  SET output = CONCAT(output, UPPER(MID(input, j + 1,1)), LOWER(SUBSTRING(input, j + 2, len - j)));

	RETURN output;
END;
//

DELIMITER ;

-- Invoke the function on a test string
SELECT CAP('  Wallet hub washington DC');