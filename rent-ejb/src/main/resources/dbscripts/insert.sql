INSERT INTO `users` (`ID`, `confirmed_BL`, `EMAIL`, `NAME`, `PASSWORD`, `PHONE`, `PHONE2`, `PHONE3`, `REGTOKEN`, `ROLE`)
VALUES
  (1, 1, 'edi.gitman@gmail.com', NULL, 'A6xnQhbz4Vx2HuGl4lXwZ5U2I8iziLRFnhP5eNfIRvQ=', NULL, NULL, NULL, '', 'USER');

INSERT INTO md_city (id, name, ordering) VALUES (1, 'Bucuresti', 1);
INSERT INTO md_city (id, name, ordering) VALUES (2, 'Cluj', 2);
INSERT INTO md_city (id, name, ordering) VALUES (3, 'Constanta', 3);
INSERT INTO md_city (id, name, ordering) VALUES (4, 'Iasi', 4);
INSERT INTO md_city (id, name, ordering) VALUES (5, 'Sibiu', 5);

INSERT INTO md_building_type (id, NAME, ORDERING) VALUES (1, 'Garsoniera', 1);
INSERT INTO md_building_type (id, NAME, ORDERING) VALUES (2, 'Ap. 1 cam', 2);
INSERT INTO md_building_type (id, NAME, ORDERING) VALUES (3, 'Ap. 2 cam', 3);
INSERT INTO md_building_type (id, NAME, ORDERING) VALUES (4, 'Ap. 3 cam', 4);
INSERT INTO md_building_type (id, NAME, ORDERING) VALUES (5, 'Ap. 4 cam', 5);
INSERT INTO md_building_type (id, NAME, ORDERING) VALUES (6, 'Casa', 6);

INSERT INTO md_neighborhood (id, name, city_id) VALUES (1, '13 Septembrie', 1);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (2, '16 Februarie', 1);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (3, 'Aviatorilor', 1);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (4, 'Aviației', 1);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (5, 'Balta Albă', 1);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (6, 'Berceni', 1);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (7, 'Bucureștii Noi', 1);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (8, 'Băneasa', 1);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (9, 'Centrul Civic', 1);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (10, 'Centrul istoric', 1);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (11, 'Colentina', 1);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (12, 'Cotroceni', 1);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (13, 'Crângași', 1);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (14, 'Dealul Spirii', 1);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (15, 'Domenii', 1);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (16, 'Dorobanți', 1);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (17, 'Dristor', 1);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (18, 'Drumul Taberei', 1);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (19, 'Dudești', 1);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (20, 'Dămăroaia', 1);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (21, 'Ferentari', 1);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (22, 'Floreasca', 1);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (23, 'Gara de Nord', 1);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (24, 'Ghencea', 1);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (25, 'Giulești', 1);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (26, 'Grivița', 1);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (27, 'Iancului', 1);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (28, 'Lipscani', 1);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (29, 'Militari', 1);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (30, 'Moșilor', 1);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (31, 'Muncii', 1);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (32, 'Obor', 1);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (33, 'Olteniței', 1);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (34, 'Pajura', 1);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (35, 'Pantelimon', 1);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (36, 'Piața Romană', 1);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (37, 'Piața Universității', 1);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (38, 'Pipera', 1);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (39, 'Primăverii', 1);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (40, 'Rahova', 1);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (41, 'Ștefan cel Mare', 1);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (42, 'Tei', 1);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (43, 'Timpuri Noi', 1);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (44, 'Tineretului', 1);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (45, 'Titan', 1);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (46, 'Unirii', 1);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (47, 'Vatra Luminoasă', 1);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (48, 'Văcărești', 1);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (49, 'Victoriei', 1);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (50, 'Vitan', 1);

INSERT INTO md_neighborhood (id, name, city_id) VALUES (51, 'Agronomie', 4);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (52, 'Alexandru cel Bun', 4);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (53, 'Arcu', 4);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (54, 'Aviației', 4);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (55, 'Baza 3', 4);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (56, 'Bucium', 4);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (57, 'Bucșinescu', 4);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (58, 'Bularga', 4);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (59, 'C.U.G. 1', 4);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (60, 'C.U.G. 2', 4);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (61, 'Canta', 4);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (62, 'Ciurchi', 4);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (63, 'Clopotari', 4);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (64, 'Copou', 4);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (65, 'Dacia', 4);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (66, 'Dimitrie Cantemir', 4);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (67, 'Dispecer', 4);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (68, 'Frumoasa', 4);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (69, 'Galata 1', 4);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (70, 'Galata 2', 4);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (71, 'Gară', 4);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (72, 'Grădinari', 4);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (73, 'Independenței', 4);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (74, 'Manta Roșie', 4);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (75, 'Metalurgie', 4);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (76, 'Mircea cel Bătrân', 4);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (77, 'Moara de Foc', 4);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (78, 'Moara de Vânt', 4);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (79, 'Nicolina 1', 4);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (80, 'Nicolina 2', 4);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (81, 'Oancea', 4);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (82, 'Podu de Fier', 4);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (83, 'Podu de Piatră', 4);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (84, 'Podu Roș', 4);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (85, 'Poitiers-Siraj', 4);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (86, 'Păcurari', 4);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (87, 'Păcureț', 4);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (88, 'Socola', 4);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (89, 'Sărărie', 4);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (90, 'Tudor Vladimirescu', 4);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (91, 'Târgu Cucului', 4);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (92, 'Tătărași Nord și Sud', 4);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (93, 'Zona Industrială Dancu', 4);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (94, 'Zona Industrială Sud', 4);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (95, 'Țesătura', 4);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (96, 'Țicău', 4);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (97, 'Țuțora', 4);

INSERT INTO md_neighborhood (id, name, city_id) VALUES (101, 'Andrei Mureșanu', 3);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (102, 'Bulgaria', 3);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (103, 'Bună Ziua', 3);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (104, 'Centru', 3);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (105, 'Dâmbul Rotund', 3);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (106, 'Gara', 3);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (107, 'Gheorgheni', 3);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (108, 'Grădini Mănăștur', 3);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (109, 'Grigorescu', 3);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (110, 'Gruia', 3);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (111, 'Hidelve', 3);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (112, 'Iris', 3);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (113, 'Între Lacuri', 3);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (114, 'Mănăștur', 3);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (115, 'Mărăști', 3);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (116, 'Someșeni', 3);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (117, 'Zorilor', 3);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (118, 'Sopor,', 3);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (119, 'Borhanci,', 3);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (120, 'Becaș,', 3);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (121, 'Făget,', 3);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (122, 'Zorilor sud (Observatorului sud, Europa)', 3);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (123, 'Lomb,', 3);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (124, 'Tineretului,', 3);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (125, 'Pata-Rât (Dallas)', 3);

INSERT INTO md_neighborhood (id, name, city_id) VALUES (130, 'Abator', 3);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (131, 'Anadalchioi', 3);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (132, 'Badea Cârțan', 3);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (133, 'Boreal', 3);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (134, 'Casa de Cultură', 3);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (135, 'Centru', 3);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (136, 'C.E.T.', 3);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (137, 'Coiciu', 3);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (138, 'Dacia', 3);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (139, 'Energia', 3);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (140, 'Faleză Nord', 3);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (141, 'Faleză Sud ( Poarta 6 )', 3);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (142, 'Far', 3);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (143, 'Gara Constanța', 3);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (144, 'Groapă', 3);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (145, 'Halta Traian', 3);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (146, 'I.C.I.L.', 3);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (147, 'I.C.Brătianu(Filimon Sârbu)', 3);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (148, 'Inel I', 3);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (149, 'Inel II', 3);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (150, 'Km. 4 (Billa)', 3);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (151, 'Km. 4-5', 3);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (152, 'Km. 5', 3);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (153, 'Mamaia', 3);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (154, 'Medeea', 3);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (155, 'Palas', 3);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (156, 'Palazu Mare', 3);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (157, 'Peninsulă', 3);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (158, 'Pescărie', 3);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (159, 'Piața Chiliei', 3);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (160, 'Piața Griviței', 3);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (161, 'Portul Constanța', 3);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (162, 'Tăbăcărie', 3);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (163, 'Tomis I', 3);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (164, 'Tomis II', 3);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (165, 'Tomis III', 3);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (166, 'Tomis IV', 3);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (167, 'Tomis Nord', 3);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (168, 'Trocadero', 3);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (169, 'Unirii', 3);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (170, 'Victoria', 3);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (171, 'Viile Noi', 3);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (172, 'Zona Industrială Constanța', 3);

INSERT INTO md_neighborhood (id, name, city_id) VALUES (180, 'Centru', 5);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (181, 'Centrul Istoric (Orașul de Sus)', 5);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (182, 'Centrul Istoric (Orașul de Jos)', 5);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (183, 'Lupeni', 5);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (184, 'Trei Stejari', 5);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (185, 'Dumbrăvii', 5);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (186, 'Vasile Aaron', 5);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (187, 'Hipodrom I', 5);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (188, 'Hipodrom II', 5);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (189, 'Hipodrom III', 5);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (190, 'Hipodrom IV', 5);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (191, 'Valea Aurie', 5);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (192, 'Tilișca', 5);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (193, 'Ștrand I', 5);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (194, 'Ștrand II', 5);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (195, 'Turnișor', 5);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (196, 'Piața Cluj', 5);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (197, 'Țiglari', 5);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (198, 'Terezian', 5);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (199, 'Reșița', 5);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (200, 'Lazaret', 5);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (201, 'Gușterița', 5);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (202, 'Broscărie (Blocuri Independența)', 5);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (203, 'Tineretului', 5);
INSERT INTO md_neighborhood (id, name, city_id) VALUES (204, 'Viile Sibiului', 5);