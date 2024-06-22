-- Donnée dans status_entity
INSERT INTO status_entity (id, name) VALUES (1, 'A venir') ON CONFLICT DO NOTHING;
INSERT INTO status_entity (id, name) VALUES (2, 'Annulé') ON CONFLICT DO NOTHING;
INSERT INTO status_entity (id, name) VALUES (3, 'Terminé') ON CONFLICT DO NOTHING;

-- Donnée dans sport_entity
INSERT INTO sport_entity (id, name) VALUES (1, 'football') ON CONFLICT DO NOTHING;
INSERT INTO sport_entity (id, name) VALUES (2, 'basket') ON CONFLICT DO NOTHING;
INSERT INTO sport_entity (id, name) VALUES (3, 'Course d''orientation') ON CONFLICT DO NOTHING;
INSERT INTO sport_entity (id, name) VALUES (4, 'Cyclisme') ON CONFLICT DO NOTHING;
