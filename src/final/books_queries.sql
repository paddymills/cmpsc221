-- part a
SELECT "title", "copyright"
FROM "titles"

-- part b
SELECT "lastName", "firstName"
FROM "authors"
WHERE "lastName" LIKE "D%"

-- part c
SELECT "firstName", "lastName", "isbn"
FROM "authors"
INNER JOIN "titles" ON "authors"."authorID"="authorISBN"."authorID"
WHERE "lastName"="Deitel" AND "firstName"="Abbey"