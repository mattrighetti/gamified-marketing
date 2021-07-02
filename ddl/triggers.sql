CREATE TRIGGER `addUsersScore`
AFTER INSERT ON `answer`
FOR EACH ROW
BEGIN
    UPDATE `user`
    SET user.`score` = (SELECT SUM(shss.`section_order`)
                        FROM `survey_section_question` AS sq
                                 INNER JOIN `survey_section` AS ss ON sq.`survey_section_id` = ss.`id`
                                 INNER JOIN `survey_header_survey_section` AS shss ON ss.id = shss.`survey_section_id`
                        WHERE NEW.`question_id` = sq.`question_id`
                       ) + user.`score`
    WHERE `user_id` = NEW.`user_id`;
END;

CREATE TRIGGER `subtractUsersScore`
AFTER DELETE ON `answer`
FOR EACH ROW
UPDATE `user`
SET user.`score` = user.`score` - (SELECT SUM(shss.`section_order`)
                                   FROM `survey_section_question` AS sq
                                            INNER JOIN `survey_section` AS ss ON sq.`survey_section_id` = ss.`id`
                                            INNER JOIN `survey_header_survey_section` AS shss ON ss.id = shss.`survey_section_id`
                                   WHERE OLD.`question_id` = sq.`question_id`
)
WHERE `user_id` = OLD.`user_id`;