-- 테스트를 위해 review 테이블을 삭제 후 다시 생성합니다.

DROP TABLE review;

CREATE TABLE IF NOT EXISTS `what_i_should_eat_test`.`review` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `content` VARCHAR(255) NULL,
  `member_id` BIGINT NOT NULL,
  `store_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_review_member_idx` (`member_id` ASC) VISIBLE,
  INDEX `fk_review_store_idx` (`store_id` ASC) VISIBLE,
  CONSTRAINT `fk_review_member`
    FOREIGN KEY (`member_id`)
    REFERENCES `what_i_should_eat_test`.`member` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_review_store1`
    FOREIGN KEY (`store_id`)
    REFERENCES `what_i_should_eat_test`.`store` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);