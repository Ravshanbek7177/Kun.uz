CREATE
OR REPLACE FUNCTION article_like_count()
    RETURNS trigger
 LANGUAGE plpgsql
AS $function$
BEGIN
 if TG_OP = 'UPDATE' then
     if NEW.EmotionStatus = 'LIKE' then
UPDATE article SET likes_count = likes_count + 1
WHERE id = NEW.article_id;
RETURN NEW;

else
UPDATE public.article  SET likes_count = likes_count - 1
WHERE id = NEW.article_id;
return NEW;
end if;
end if;

END;
$function$

CREATE TRIGGER article_like_trigger
    AFTER INSERT
    ON article.likes
    FOR EACH ROW
    EXECUTE FUNCTION article_like_count_function();
