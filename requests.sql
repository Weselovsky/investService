SELECT name, type, ticker, price
FROM papers
ORDER BY id
LIMIT 9;

UPDATE papers
SET price = price + 1
WHERE id = 1
RETURNING name,type, price;

INSERT INTO papers (name, type, ticker, price, profit, sector)
VALUES ('VTB', 'Stock', 'PVTB', 150, 40, 'Finance')
RETURNING name, type, ticker, price, profit, sector
;
DELETE
FROM papers
WHERE name = 'Apple'
RETURNING name, type, ticker, price, profit, sector
;

SELECT name, price, qty, type
FROM sales
ORDER BY type
;

SELECT paper_id, type, name, price, qty
FROM sales
WHERE type = 'buy'
;