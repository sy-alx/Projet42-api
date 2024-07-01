-- Création de la table status_entity
CREATE TABLE IF NOT EXISTS status_entity (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

-- Données dans status_entity
INSERT INTO status_entity (id, name) VALUES (1, 'A venir') ON CONFLICT DO NOTHING;
INSERT INTO status_entity (id, name) VALUES (2, 'Annulé') ON CONFLICT DO NOTHING;
INSERT INTO status_entity (id, name) VALUES (3, 'Terminé') ON CONFLICT DO NOTHING;

-- Création de la table sport_entity
CREATE TABLE IF NOT EXISTS sport_entity (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

-- Données dans sport_entity
INSERT INTO sport_entity (id, name) VALUES (1, 'football') ON CONFLICT DO NOTHING;
INSERT INTO sport_entity (id, name) VALUES (2, 'basket') ON CONFLICT DO NOTHING;
INSERT INTO sport_entity (id, name) VALUES (3, 'Course d''orientation') ON CONFLICT DO NOTHING;
INSERT INTO sport_entity (id, name) VALUES (4, 'Cyclisme') ON CONFLICT DO NOTHING;

-- Création de la table event_entity
CREATE TABLE IF NOT EXISTS event_entity (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    date TIMESTAMP NOT NULL
);

-- Création de la table event_participant_entity
CREATE TABLE IF NOT EXISTS event_participant_entity (
    id BIGSERIAL PRIMARY KEY,
    event_id BIGINT NOT NULL,
    participant_id VARCHAR(255) NOT NULL,
    FOREIGN KEY (event_id) REFERENCES event_entity(id)
);


INSERT INTO status_entity (id, name) VALUES (1, 'A venir') ON CONFLICT DO NOTHING;
INSERT INTO status_entity (id, name) VALUES (2, 'Annulé') ON CONFLICT DO NOTHING;
INSERT INTO status_entity (id, name) VALUES (3, 'Terminé') ON CONFLICT DO NOTHING;

-- Donnée dans sport_entity
INSERT INTO sport_entity (id, name) VALUES (1, 'football') ON CONFLICT DO NOTHING;
INSERT INTO sport_entity (id, name) VALUES (2, 'basket') ON CONFLICT DO NOTHING;
INSERT INTO sport_entity (id, name) VALUES (3, 'Course d''orientation') ON CONFLICT DO NOTHING;
INSERT INTO sport_entity (id, name) VALUES (4, 'Cyclisme') ON CONFLICT DO NOTHING;
