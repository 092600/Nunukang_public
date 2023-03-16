-- nunukang.alert definition

CREATE TABLE `alert` (
  `alert_type` varchar(31) NOT NULL,
  `alert_id` bigint NOT NULL,
  `created_date` datetime DEFAULT NULL,
  `is_read` bit(1) DEFAULT NULL,
  `alerting_user_user_id` bigint DEFAULT NULL,
  `make_alert_user_user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`alert_id`),
  KEY `FKd6m24dww9ao9vqbvipqx1yhtp` (`alerting_user_user_id`),
  KEY `FK4nuryi3fthcgyvgowpwyyfrjr` (`make_alert_user_user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- nunukang.black_progy definition

CREATE TABLE `black_progy` (
  `species` int DEFAULT NULL,
  `id` bigint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- nunukang.comment definition

CREATE TABLE `comment` (
  `comment_id` bigint NOT NULL,
  `create_date` date DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `post_id` bigint DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`comment_id`),
  KEY `FKs1slvnkuemjsq2kj4h3vhx7i1` (`post_id`),
  KEY `FK8kcum44fvpupyw6f5baccx25c` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- nunukang.comment_created_alert definition

CREATE TABLE `comment_created_alert` (
  `message` varchar(255) DEFAULT NULL,
  `alert_id` bigint NOT NULL,
  PRIMARY KEY (`alert_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- nunukang.favorite_spots_id definition

CREATE TABLE `favorite_spots_id` (
  `user_id` bigint NOT NULL,
  `favorite_spots_id` bigint DEFAULT NULL,
  KEY `FKyct0ix4b7l55qcfnnpkidfy0` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- nunukang.fish definition

CREATE TABLE `fish` (
  `fish_id` bigint NOT NULL,
  `created_date` datetime DEFAULT NULL,
  `fish_score` double DEFAULT NULL,
  `fish_size` double DEFAULT NULL,
  `picture_name` varchar(255) DEFAULT NULL,
  `picture_path` varchar(255) DEFAULT NULL,
  `species` int DEFAULT NULL,
  `fish_catcher_user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`fish_id`),
  KEY `FKesan71mv5tve6sc3oj4uxwikw` (`fish_catcher_user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- nunukang.fish_for_score definition

CREATE TABLE `fish_for_score` (
  `fish_type` varchar(31) NOT NULL,
  `id` bigint NOT NULL,
  `fish_score` int DEFAULT NULL,
  `fish_size` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- nunukang.fishing_spot definition

CREATE TABLE `fishing_spot` (
  `fishing_spot_id` bigint NOT NULL,
  `address` varchar(70) DEFAULT NULL,
  `capacity` int DEFAULT NULL,
  `latitude` varchar(50) DEFAULT NULL,
  `longitude` varchar(50) DEFAULT NULL,
  `name` varchar(30) DEFAULT NULL,
  `number` varchar(15) DEFAULT NULL,
  `price` varchar(100) DEFAULT NULL,
  `type` int DEFAULT NULL,
  PRIMARY KEY (`fishing_spot_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- nunukang.fishing_spot_sequence_generator definition

CREATE TABLE `fishing_spot_sequence_generator` (
  `next_val` bigint DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- nunukang.follow_alert definition

CREATE TABLE `follow_alert` (
  `message` varchar(255) DEFAULT NULL,
  `alert_id` bigint NOT NULL,
  PRIMARY KEY (`alert_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- nunukang.hibernate_sequence definition

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- nunukang.korea_rockfish definition

CREATE TABLE `korea_rockfish` (
  `species` int DEFAULT NULL,
  `id` bigint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- nunukang.olive_flounder definition

CREATE TABLE `olive_flounder` (
  `species` int DEFAULT NULL,
  `id` bigint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- nunukang.post definition

CREATE TABLE `post` (
  `post_id` bigint NOT NULL,
  `create_date` date DEFAULT NULL,
  `content` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `view_cnt` bigint DEFAULT NULL,
  `post_writer_user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`post_id`),
  KEY `FKkr98vednndsdb9pfi7jnyr3rb` (`post_writer_user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- nunukang.post_create_alert definition

CREATE TABLE `post_create_alert` (
  `message` varchar(255) DEFAULT NULL,
  `alert_id` bigint NOT NULL,
  PRIMARY KEY (`alert_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- nunukang.post_image definition

CREATE TABLE `post_image` (
  `post_image_id` bigint NOT NULL,
  `image_save_path` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `post_id` bigint DEFAULT NULL,
  PRIMARY KEY (`post_image_id`),
  KEY `FKsip7qv57jw2fw50g97t16nrjr` (`post_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- nunukang.post_image_generator definition

CREATE TABLE `post_image_generator` (
  `next_val` bigint DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- nunukang.post_like_alert definition

CREATE TABLE `post_like_alert` (
  `message` varchar(255) DEFAULT NULL,
  `alert_id` bigint NOT NULL,
  PRIMARY KEY (`alert_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- nunukang.post_like_user definition

CREATE TABLE `post_like_user` (
  `post_id` bigint NOT NULL,
  `post_like_user_id` bigint NOT NULL,
  KEY `FKrgydy63md3vicg3pf2ichu1b6` (`post_like_user_id`),
  KEY `FK2xf2rjv2889mn47uaskdb57m0` (`post_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- nunukang.post_sequence_generator definition

CREATE TABLE `post_sequence_generator` (
  `next_val` bigint DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- nunukang.post_tagged_alert definition

CREATE TABLE `post_tagged_alert` (
  `message` varchar(255) DEFAULT NULL,
  `alert_id` bigint NOT NULL,
  PRIMARY KEY (`alert_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- nunukang.red_seabream definition

CREATE TABLE `red_seabream` (
  `species` int DEFAULT NULL,
  `id` bigint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- nunukang.rock_bream definition

CREATE TABLE `rock_bream` (
  `species` int DEFAULT NULL,
  `id` bigint NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- nunukang.`user` definition

CREATE TABLE `user` (
  `user_id` bigint NOT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `background_img` varchar(255) DEFAULT NULL,
  `intro` varchar(255) DEFAULT NULL,
  `profile_img` varchar(255) DEFAULT NULL,
  `role` int DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- nunukang.user_followers definition

CREATE TABLE `user_followers` (
  `user_id` bigint NOT NULL,
  `followers_user_id` bigint NOT NULL,
  KEY `FK743026b28m57781i0def9ixn5` (`followers_user_id`),
  KEY `FKokc5w6fibhnthvwnxjxyrlfc1` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- nunukang.user_following definition

CREATE TABLE `user_following` (
  `user_id` bigint NOT NULL,
  `following_user_id` bigint NOT NULL,
  KEY `FKguvum44ye40dnbwsppnu90k5` (`following_user_id`),
  KEY `FKn4mj5gtsm47fikwbu41b6wi9k` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- nunukang.user_sequence_generator definition

CREATE TABLE `user_sequence_generator` (
  `next_val` bigint DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- nunukang.user_tagged_posts definition

CREATE TABLE `user_tagged_posts` (
  `user_id` bigint NOT NULL,
  `tagged_post_id` bigint NOT NULL,
  KEY `FKnq5qmnp9c60v8ugop25oradyf` (`tagged_post_id`),
  KEY `FK9t7fiwbs24nkb0k5bhdos1l14` (`user_id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;