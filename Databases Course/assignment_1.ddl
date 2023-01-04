connect to SE3DB3;

CREATE TABLE PERSON (
  personID CHAR(30) NOT NULL,
  firstName VARCHAR(20),
  lastName VARCHAR(20),
  occupation VARCHAR(20),
  addressStreet VARCHAR(20),
  addressCity VARCHAR(20),
  addressProvince VARCHAR(20),
  age SMALLINT,
  PRIMARY KEY (personID)
);

CREATE TABLE CONTACT (
  contactNumber BIGINT NOT NULL,
  contactType VARCHAR(20) CHECK (contactType = 'Home' OR contactType = 'Work' OR contactType = 'Cell'),
  person CHAR(30) NOT NULL REFERENCES PERSON(personID),
  PRIMARY KEY (contactNumber)
);

CREATE TABLE MOVIEDIRECTOR (
  directorPersonID CHAR(30) NOT NULL REFERENCES PERSON(personID),
  yearsOfExperience SMALLINT,
  studio VARCHAR(20),
  netWorth REAL,
  PRIMARY KEY (directorPersonID)
);

CREATE TABLE MOVIEACTOR (
  actorPersonID CHAR(30) NOT NULL REFERENCES PERSON(personID),
  agentName VARCHAR(20),
  netWorth REAL,
  PRIMARY KEY (actorPersonID)
);

CREATE TABLE REWARDSCARD (
  cardNumber INT NOT NULL,
  balance INT,
  activationDate DATE,
  PRIMARY KEY (cardNumber)
);

CREATE TABLE MOVIEGOER (
  goerPersonID CHAR(30) NOT NULL REFERENCES PERSON(personID),
  rewardsCard INT REFERENCES REWARDSCARD(cardNumber),
  PRIMARY KEY (goerPersonID)
);

CREATE TABLE MOVIE ( 
  movieID CHAR(30) NOT NULL,
  title VARCHAR(50),
  genre CHAR(1) CHECK (genre = 'D' OR genre = 'C' OR genre = 'H' OR genre = 'F' OR genre = 'R' OR genre = 'S'),
  releaseDate DATE,
  grossEarnings REAL,
  director CHAR(30) NOT NULL REFERENCES MOVIEDIRECTOR(directorPersonID),
  actor CHAR(30) NOT NULL,
  PRIMARY KEY (movieID)
);

CREATE TABLE STARS (
  actor CHAR(30) NOT NULL REFERENCES MOVIEACTOR(actorPersonID),
  movie CHAR(30) NOT NULL REFERENCES MOVIE(movieID),
  PRIMARY KEY (actor, movie)
);

ALTER TABLE MOVIE
ADD FOREIGN KEY (movieID, actor) REFERENCES STARS(movie, actor);

CREATE TABLE AWARD (
  awardName VARCHAR(30) NOT NULL,
  awardYear SMALLINT NOT NULL,
  movieBudget REAL,
  director CHAR(30) NOT NULL REFERENCES MOVIEDIRECTOR(directorPersonID),
  movie CHAR(30) NOT NULL REFERENCES MOVIE(movieID),
  PRIMARY KEY (awardName, awardYear)
);

CREATE TABLE OSCAR (
  movieRole VARCHAR(30) NOT NULL,
  prodRole VARCHAR(20) CHECK (prodRole = 'lead actor' OR prodRole = 'supporting actor' OR prodRole = 'understudy'),
  year SMALLINT NOT NULL,
  actor VARCHAR(30) NOT NULL REFERENCES MOVIEACTOR(actorPersonID),
  movie VARCHAR(30) NOT NULL REFERENCES MOVIE(movieID),
  PRIMARY KEY (movieRole, year)
);

CREATE TABLE THEATRE (
  theatreID CHAR(30) NOT NULL,
  theatreName VARCHAR(30),
  addressStreet VARCHAR(20),
  addressCity VARCHAR(20),
  addressProvince VARCHAR(20),
  numOfScreens SMALLINT,
  PRIMARY KEY (theatreID)
);

CREATE TABLE SHOWN (
  movie CHAR(30) NOT NULL REFERENCES MOVIE(movieID),
  theatreID CHAR(30) NOT NULL REFERENCES THEATRE(theatreID),
  screeningTime TIME NOT NULL,
  dayOfTheWeek VARCHAR(20) NOT NULL CHECK (dayOfTheWeek = 'Monday' OR dayOfTheWeek = 'Tuesday' OR dayOfTheWeek = 'Wednesday' OR dayOfTheWeek = 'Thursday' OR dayOfTheWeek = 'Friday' OR dayOfTheWeek = 'Saturday' OR dayOfTheWeek = 'Sunday'),
  ticketPrice REAL NOT NULL,
  screenNumber SMALLINT NOT NULL,
  PRIMARY KEY (movie, theatreID, screeningTime, dayOfTheWeek, ticketPrice, screenNumber)
);

CREATE TABLE VISITS (
  movieGoer CHAR(30) NOT NULL REFERENCES MOVIEGOER(goerPersonID),
  theatre CHAR(30) NOT NULL REFERENCES THEATRE(theatreID),
  paymentMethod VARCHAR(20) CHECK (paymentMethod = 'cash' OR paymentMethod = 'credit' OR paymentMethod = 'debit'),
  visitDate DATE NOT NULL,
  ticketPrice REAL,
  PRIMARY KEY (movieGoer, theatre, visitDate)
);

CREATE TABLE TRANSACTION (
  transactionID CHAR(30) NOT NULL,
  amountPaid REAL,
  movieGoer CHAR(30) NOT NULL,
  PRIMARY KEY (transactionID)
);

CREATE TABLE MAKE (
  transactionID CHAR(30) NOT NULL REFERENCES TRANSACTION(transactionID),
  movieGoer CHAR(30) NOT NULL REFERENCES MOVIEGOER(goerPersonID),
  transactionDate DATE,
  paymentMethod VARCHAR(20) CHECK (paymentMethod = 'cash' OR paymentMethod = 'credit' OR paymentMethod = 'debit'),
  PRIMARY KEY (transactionID, movieGoer)
);

ALTER TABLE TRANSACTION
ADD FOREIGN KEY (transactionID, movieGoer) REFERENCES MAKE(transactionID, movieGoer);

CREATE TABLE PRODUCT (
  skuID CHAR(30) NOT NULL,
  prodName VARCHAR(30),
  category VARCHAR(20) CHECK (category = 'candy' OR category = 'souvenir' OR category = 'popcorn' OR category = 'beverage' OR category = 'toy'),
  price REAL,
  PRIMARY KEY (skuID)
);

ALTER TABLE TRANSACTION
ADD product CHAR(30) NOT NULL REFERENCES PRODUCT(skuID) DEFAULT 'defaultProductID';

CREATE TABLE INVOLVES (
  transactionID CHAR(30) NOT NULL REFERENCES TRANSACTION(transactionID),
  product CHAR(30) NOT NULL REFERENCES PRODUCT(skuID),
  quantity INT,
  PRIMARY KEY (transactionID, product)
);

ALTER TABLE TRANSACTION
ADD FOREIGN KEY (transactionID, product) REFERENCES INVOLVES(transactionID, product);

CREATE TABLE CONCESSIONSTAND (
  standType VARCHAR(10) NOT NULL CHECK (standType = 'food' OR standType = 'souvenir'),
  theatreID CHAR(30) NOT NULL REFERENCES THEATRE(theatreID),
  product CHAR(30) NOT NULL,
  PRIMARY KEY(standType, theatreID)
);

CREATE TABLE SELLS (
  product CHAR(30) NOT NULL REFERENCES PRODUCT(skuID),
  theatreID CHAR(30) NOT NULL,
  standType VARCHAR(10) NOT NULL,
  FOREIGN KEY (theatreID, standType) REFERENCES CONCESSIONSTAND(theatreID, standType),
  PRIMARY KEY (product, theatreID, standType)
);

ALTER TABLE CONCESSIONSTAND
FOREIGN KEY (standType, theatreID, product) REFERENCES SELLS(standType, theatreID, product);

connect reset;