============================================ R U ============================================

confirm - собираем итемы, которые стакаются, чтобы их взять нужно подтвердить количество, поэтому нужен отдельный алгоритм на поднятие лута.

empty - Сюда ничего не добавлять, нужен исключительно для определения пустой ячейки лута.

exception - Здесь собираем итемы, которые проходят по цвету рамки, но мы не хотим их подбирать.

frame - Сюда ничего не добавлять, нужен для определения цвета рамки.

unknown - Сюда попадает лут, который не опознан ни для одной из категорий (usefull, confirm, exception). Цвет рамки не учитывается.

usefull - Добавляем сюда лут, который хотим подбирать без учета рамки. Например осколки реликвий имеют зеленую рамку, но мы хотим чтобы бот их поднимал при отключенном фильтре.

============================================ E N G ============================================

confirm - we collect items that stack, in order to take them, you need to confirm the number, so a separate algorithm is needed to raise the loot.

empty - Do not add anything here, it is needed solely to define an empty loot cell.

exception - Here we collect items that pass along the border color, but we do not want to pick them up.

frame - Do not add anything here, it is needed to define the frame color.

unknown - This contains loot that is not recognized for any of the categories (usefull, confirm, exception). Border color is not taken into account.

usefull - Add here the loot that we want to pick up without taking into account the frame. For example, fragments of relics have a green border, but we want the bot to pick them up with the filter turned off.