
CREATE FUNCTION function_create_dialog_in_ReadTable() RETURNS trigger AS $emp_stamp$
BEGIN

  INSERT INTO "DialogsLastSessionsChanges" SELECT NEW.dialog_id, now() last_change_time;
  RETURN NEW;
END;
$emp_stamp$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_create_dialog_in_ReadTable AFTER INSERT ON "ReadTable"
  FOR EACH ROW EXECUTE PROCEDURE function_create_dialog_in_ReadTable();