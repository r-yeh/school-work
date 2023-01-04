--Ryan Yeh, 400254730

connect to se3db3;

/* Question 1a */
SELECT *
FROM Movie
WHERE GrossEarnings > 1200000 AND GrossEarnings < 1500000;

/* Question 1b */
SELECT DISTINCT p.ID, p.FirstName, p.LastName, a.OscarYear
FROM Person p, Act a
WHERE p.ID = a.ActorID AND a.OscarYear IS NOT NULL AND 2 <= (SELECT COUNT(a2.OscarYear) FROM Act a2 WHERE a2.ActorID = a.ActorID);

/* Question 2 */
SELECT c.ID, c.Points, p.ID, p.FirstName, p.LastName
FROM Card c, Person p, Has h
WHERE h.GoerID = p.ID AND h.CardID = c.ID AND c.Points = (SELECT MAX(c2.Points) FROM Card c2);

/* Question 3a */
SELECT m.ID, m.Genre, m.Title
FROM Movie m, Person p, Direct d
WHERE p.FirstName = 'Gennadi' AND p.ID = d.DirectorID AND d.MovieID = m.ID
AND m.GrossEarnings =  (SELECT MAX(m2.GrossEarnings) FROM Movie m2, Person p2, Direct d2 WHERE p2.FirstName = 'Gennadi' AND p2.ID = d2.DirectorID AND d2.MovieID = m2.ID);

/* Question 3b */
SELECT m.ID, m.Genre, m.Title
From Movie m
WHERE m.GrossEarnings = (SELECT MAX(m2.GrossEarnings) From Movie m2);

/* Question 4a */
SELECT COUNT(*) AS ActorCount
FROM Person p
WHERE p.ID IN (SELECT a2.ActorID FROM Act a2 GROUP BY a2.ActorID HAVING COUNT(*) > 5);

/* Question 4b */
SELECT p.ID, p.FirstName, p.LastName
FROM Person p
WHERE p.ID IN (SELECT a.ActorID FROM Act a GROUP BY a.ActorID HAVING COUNT(*) >= ALL (SELECT COUNT(*) FROM Act a2 GROUP BY a2.ActorID));

/* Question 5 */
SELECT p.FirstName, p.LastName, SUM(t.PaidAmount) AS AmountPaid FROM Person p, Make m, Transaction t
WHERE YEAR(m.Date) = 2015 AND m.GoerID = p.ID AND m.TransactionID = t.ID
GROUP BY p.FirstName, p.LastName
HAVING SUM(t.PaidAmount) > 150
ORDER BY AmountPaid DESC;

/* Question 6a */
SELECT d.StudioAffiliation, COUNT(DISTINCT m.title) AS MovieCount
FROM Direct dir, Director d, Movie m
WHERE dir.DirectorID = d.ID AND m.ID = dir.movieID
GROUP BY d.StudioAffiliation;

/* Question 6b */
SELECT d.StudioAffiliation, COUNT(DISTINCT m.title) AS MovieCount
FROM Direct dir, Director d, Movie m
WHERE dir.DirectorID = d.ID AND dir.movieID = m.ID AND dir.Budget >= 7000000 AND dir.AwardName IS NOT NULL
GROUP BY d.StudioAffiliation;

/* Question 7 */
SELECT DISTINCT m.ID, m.Genre, m.Title, m.ReleaseDate
FROM Movie m, Show s, Show s2, MovieTheater mt, MovieTheater mt2, Visit v, Visit v2, Goer g, Actor a
WHERE m.ID = s.movieID AND s.movieTheaterID = mt.ID AND mt.ID = v.movieTheaterID AND v.GoerID = g.ID
AND m.ID = s2.movieID AND s2.movieTheaterID = mt2.ID AND mt2.ID = v2.movieTheaterID AND v2.GoerID = a.ID;

/* Question 8a */
SELECT c.MovieTheaterID, c.Type, SUM(b.Quantity) AS QuantitySold
FROM ConcessionStand c, Sold s, Belong b
WHERE c.MovieTheaterID = s.MovieTheaterID AND s.ProductID = b.ProductID AND c.Type = s.Type
GROUP BY c.MovieTheaterID, c.Type
HAVING SUM(b.Quantity) > 50
ORDER BY SUM(b.Quantity) ASC;

/* Question 8b */
SELECT c.MovieTheaterID, c.Type, SUM(t.PaidAmount) AS TotalSales
FROM ConcessionStand c, Sold s, Belong b, Transaction t
WHERE c.MovieTheaterID = s.MovieTheaterID AND s.ProductID = b.ProductID AND c.Type = s.Type AND b.TransactionID = t.ID
GROUP BY c.MovieTheaterID, c.Type
HAVING SUM(t.PaidAmount) > 570
ORDER BY SUM(t.PaidAmount) ASC;

/* Question 9a */
SELECT DISTINCT mt.ID, mt.Name, mt.Province
FROM MovieTheater mt, Show s, Show s2, Movie m, Movie m2
WHERE s.MovieTheaterID = mt.ID AND s2.MovieTheaterID = mt.ID AND s.MovieID = m.ID AND s2.MovieID = m2.ID AND s.Day = s2.Day AND m.Genre = 'D' AND m2.Genre = 'R';

/* Question 9b */
SELECT DISTINCT mt.ID, mt.Name, mt.Province
FROM MovieTheater mt
WHERE mt.Screens >= 4 AND mt.ID IN (SELECT v.MovieTheaterID FROM Visit v GROUP BY v.MovieTheaterID, YEAR(Date) HAVING SUM(Price) < 10000);

/* Question 10 */
SELECT p.Category, SUM(p.Price*b.Quantity) AS TotalSalesAmount
FROM Product p, Belong b
WHERE b.ProductID = p.ID
GROUP BY p.Category
ORDER BY SUM(p.Price*b.Quantity) DESC FETCH FIRST ROW ONLY;