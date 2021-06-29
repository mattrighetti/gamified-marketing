
CREATE TRIGGER `addUsersScore`
    AFTER INSERT ON `answer`
    FOR EACH ROW
BEGIN
    IF EXISTS((SELECT leaderboard.`user_id` FROM `leaderboard` WHERE leaderboard.`user_id` = NEW.`user_id`)) THEN
        UPDATE `leaderboard`
        SET leaderboard.`points` = (SELECT SUM(shss.`section_order`)
                                    FROM `survey_section_question` AS sq
                                             INNER JOIN `survey_section` AS ss ON sq.`survey_section_id` = ss.`id`
                                             INNER JOIN `survey_header_survey_section` AS shss ON ss.id = shss.`survey_section_id`
                                    WHERE NEW.`question_id` = sq.`question_id`
                                   ) + leaderboard.`points`
        WHERE `user_id` = NEW.`user_id`;
    ELSE
        INSERT INTO `leaderboard`
        SELECT NEW.`user_id`, SUM(shss.`section_order`)
        FROM `survey_section_question` AS `sq`
                 INNER JOIN `survey_section` AS `ss` ON sq.`survey_section_id` = ss.`id`
                 INNER JOIN `survey_header_survey_section` AS shss ON ss.`id` = shss.`survey_section_id`
        WHERE sq.`question_id` = NEW.`question_id`;
    END IF;
END;

CREATE TRIGGER `subtractUsersScore`
    BEFORE DELETE ON `answer`
    FOR EACH ROW
    UPDATE `leaderboard`
    SET leaderboard.`points` = leaderboard.`points` - (SELECT SUM(shss.`section_order`)
                                                       FROM `survey_section_question` AS sq
                                                                INNER JOIN `survey_section` AS ss ON sq.`survey_section_id` = ss.`id`
                                                                INNER JOIN `survey_header_survey_section` AS shss ON ss.id = shss.`survey_section_id`
                                                       WHERE OLD.`question_id` = sq.`question_id`
    )
    WHERE `user_id` = OLD.`user_id`;