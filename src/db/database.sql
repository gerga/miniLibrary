create table genre(
    id blob primary key,
    code int UNIQUE NOT NULL,
    name text
);

create table book(
    id blob primary key,
    code int UNIQUE NOT NULL,
    name varchar(70),
    author varchar(70),
    isbn char(13),
    year int,
    edition int,
    pages int,
    genre_id blob references genre(id) on update cascade,
    status int
);

create table person(
    id blob primary key,
    name varchar(70),
    email varchar(50),
    phone varchar(20)
);

create table client(
    id blob primary key,
    code int UNIQUE NOT NULL,
    person_id blob references person(id) on update cascade
);

create table librarian(
    id blob primary key,
    code int NOT NULL UNIQUE,
    cpf char(15) NOT NULL UNIQUE,
    person_id blob references person(id) on update cascade
);

create table loan(
    id blob primary key,
    code int UNIQUE NOT NULL,
    lend_date date NOT NULL,
    client_id references client(id) on update cascade,
    librarian_id references librarian(id) on update cascade,
    return_date date NOT NULL,
    status int,
    returned_date date
);

create table loan_book(
    id blob primary key,
    book_id references book(id) on update cascade,
    loan_id references loan(id) on update cascade
);

create table booking(
    id blob primary key,
    client_id references client(id) on update cascade,
    book_id references book(id) on update cascade,
    responsible_id references librarian(id) on update cascade,
    start_date date,
    end_date date,
    return_date date
);