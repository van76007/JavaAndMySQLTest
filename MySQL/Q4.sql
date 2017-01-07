-- Solution for Question 4. Tested on MySQL 5.7
DROP TABLE IF EXISTS bugs;
CREATE TABLE bugs(id INT, open_date DATE, close_date DATE DEFAULT NULL, severity VARCHAR(50));

-- Populate the table by some data
INSERT INTO bugs VALUES (1, '2012-01-01', '2012-01-05', 'D'), (2, '2012-01-04', '2012-01-09', 'E'), (3, '2012-01-04', '2012-01-08', 'M'), (4, '2012-01-03', NULL, 'M'), (4, '2011-12-30', NULL, 'M');

SELECT * FROM bugs;

-- This is the query that answer the question
SELECT * FROM bugs WHERE open_date <= '2012-01-02' AND (close_date IS NOT NULL AND close_date >= '2012-01-02');

-- I prefer this query as I assume that a bug is still open if the close date is not yet defined
SELECT * FROM bugs WHERE open_date <= '2012-01-02' AND (close_date IS NULL OR close_date >= '2012-01-02');