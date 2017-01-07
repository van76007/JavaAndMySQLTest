-- Solution for Question 1. Tested on MySQL 5.7
SELECT	name,
        votes,
        @curRank := @curRank + 1 AS rank
FROM    votes v, (SELECT @curRank := 0) r
ORDER BY	votes desc;