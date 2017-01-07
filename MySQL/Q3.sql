-- Solution for Question 3. Tested on MySQL 5.7
SELECT * FROM sometbl;

DROP PROCEDURE IF EXISTS split_table_column;

DELIMITER //

-- This procedure accepts table name and delimiter character used in its column text
CREATE PROCEDURE split_table_column(IN table_name VARCHAR(255), IN delim VARCHAR(255))

BEGIN
  
  -- FIRST, create a temporary table which is a copy of the given table
	SET @temp_query = 'DROP TEMPORARY TABLE IF EXISTS temp_cursor_table';
	PREPARE pst FROM @temp_query;
	EXECUTE pst;
	DEALLOCATE PREPARE pst;

	SET @temp_table_query='CREATE TEMPORARY TABLE temp_cursor_table';
	SET @temp_table_query=concat( @temp_table_query, ' SELECT id, name FROM ' );
	SET @temp_table_query=concat( @temp_table_query, table_name );
	PREPARE pst FROM @temp_table_query;
	EXECUTE pst;
	DEALLOCATE PREPARE pst;

	BEGIN
		DECLARE id INT DEFAULT 0;
		DECLARE column_text TEXT;
		DECLARE i INT DEFAULT 0;
		DECLARE occurance INT DEFAULT 0;
		DECLARE splitted_value TEXT;
		DECLARE done INT DEFAULT 0;

		-- Secondly, create a read cursor for the temporary table
		DECLARE cur CURSOR FOR SELECT * FROM temp_cursor_table;
		DECLARE continue HANDLER FOR NOT FOUND SET done = 1;
		
		-- Thirdly, create a table to store result of splitting text
		DROP TEMPORARY TABLE IF EXISTS tblResult;
		CREATE TEMPORARY TABLE tblResult(id INT, sname VARCHAR(50)) ENGINE MEMORY;
		
		-- Now, loop over temporary table to read
		OPEN cur;
			read_loop: LOOP
				FETCH cur INTO id, column_text;

				-- Check if this is end of the loop to terminate
				IF done THEN
					LEAVE read_loop;
				END IF;

        -- Inner loop to split column text
				SET occurance = (SELECT LENGTH(column_text)
                                 - LENGTH(REPLACE(column_text, delim, ''))
                                 +1);
				SET i=1;
				WHILE i <= occurance DO
					SET splitted_value =
						(SELECT REPLACE(SUBSTRING(SUBSTRING_INDEX(column_text, delim, i),
								LENGTH(SUBSTRING_INDEX(column_text, delim, i - 1)) + 1), delim, ''));

					INSERT INTO tblResult VALUES (id, splitted_value);
						
					SET i = i + 1;
				END WHILE;
			END LOOP;
		CLOSE cur;
	END;
	
	-- Print out result to verify
	SELECT * FROM tblResult;

END;
//

DELIMITER ;

CALL split_table_column('sometbl','|');