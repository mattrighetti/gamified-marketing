CREATE VIEW `leaderboard` AS
SELECT a.`user_id`, SUM(shss.`section_order`) AS `points`
FROM `answer` AS `a`
         INNER JOIN `survey_section_question` AS `sq` ON a.`question_id` = sq.`question_id`
         INNER JOIN `survey_section` AS `ss` ON sq.`survey_section_id` = ss.`id`
         INNER JOIN `survey_header_survey_section` AS shss ON ss.`id` = shss.`survey_section_id`
GROUP BY a.`user_id`
ORDER BY `points` DESC;