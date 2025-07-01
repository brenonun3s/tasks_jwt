CREATE TABLE tb_tasks (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255),
    description TEXT,
    due_date DATE,
    completed BOOLEAN,
    date_creation DATE,
    user_id BIGINT,
    CONSTRAINT fk_user_task FOREIGN KEY (user_id) REFERENCES tb_users(id)
);
