# Write your MySQL query statement below
WITH CTE AS (SELECT score, DENSE_RANK() OVER (ORDER BY score DESC) AS `rank` FROM Scores)

SELECT score, `rank` FROM CTE ORDER BY `rank`;