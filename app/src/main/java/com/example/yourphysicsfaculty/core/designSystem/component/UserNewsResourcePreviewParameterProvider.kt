package com.example.yourphysicsfaculty.core.designSystem.component

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.example.yourphysicsfaculty.core.designSystem.component.PreviewParameterData.newsResources
import com.example.yourphysicsfaculty.core.model.Topic
import com.example.yourphysicsfaculty.core.model.UserNewsResource
import java.time.LocalDate
import java.util.Calendar
import java.util.Date

/**
 * This [PreviewParameterProvider](https://developer.android.com/reference/kotlin/androidx/compose/ui/tooling/preview/PreviewParameterProvider)
 * provides list of [UserNewsResource] for Composable previews.
 */
class UserNewsResourcePreviewParameterProvider : PreviewParameterProvider<List<UserNewsResource>> {

    @RequiresApi(Build.VERSION_CODES.O)
    override val values: Sequence<List<UserNewsResource>> = sequenceOf(newsResources)
}

object PreviewParameterData {
    val newsResources = listOf<UserNewsResource>(
        UserNewsResource(
            id = "0",
            title = "Дистанционный день открытых дверей",
            content = "ДИСТАНЦИОННЫЙ ДЕНЬ ОТКРЫТЫХ ДВЕРЕЙ УЖЕ СЕГОДНЯ!\n" +
                    "\n" +
                    "Сегодня в 14:00 начнётся трансляция дистанционного дня открытых дверей.\n" +
                    "\n" +
                    "На ней вы сможете услышать выступление декана, узнать про новое направление на физфаке, про особенности поступления, а также задать все интересующие вас вопросы. ",
            url = "https://open.phys.msu.ru/events/distantsionnyy-den-otkrytykh-dverey/",
            headerImageUrl = "https://open.phys.msu.ru/upload/iblock/d5f/uy9nvzu3c34vwooxawwzz4ve2tkz8sn7/g-1XlanIPLU.jpg",
            publishDate = "14.04.2024",
            type = "ДОД 24",
            topics = listOf(Topic("0", "абитуриентам"), Topic("0", "день открытых дверей")),
            isSaved = true,
            hasBeenViewed = false
        ),
        UserNewsResource(
            id = "1",
            title = "Как мы их «видим» и зачем нам это нужно?",
            content = " Физический факультет приглашает на «Университетскую субботу».  В рамках мероприятия пройдет интерактивная лекция на тему:\n" +
                    "\n" +
                    "Атомы, ядра, частицы. Как мы их «видим» и зачем нам это нужно? ",
            url = "https://open.phys.msu.ru/events/kak-my-ikh-vidim-i-zachem-nam-eto-nuzhno/",
            headerImageUrl = "https://open.phys.msu.ru/upload/iblock/7c3/pd6znw5m1lyq3ha2n14vzw2oygjigl7p/%D0%90%D1%82%D0%BE%D0%BC%D1%8B%20%D1%8F%D0%B4%D1%80%D0%B0%20%D1%87%D0%B0%D1%81%D1%82%D0%B8%D1%86%D1%8B.png",
            publishDate = "21.10.2023 17:00:00",
            type = "Университетские субботы",
            topics = listOf(Topic("0", "студентам"), Topic("0", "университетские субботы")),
            isSaved = false,
            hasBeenViewed = false
        ),
        UserNewsResource(
            id = "2",
            title = "Как прогнозируют волны цунами?",
            content = "Физический факультет приглашает на «Университетскую субботу».  В рамках мероприятия пройдет интерактивная лекция на тему: «Как прогнозируют волны цунами?»",
            url = "https://open.phys.msu.ru/events/kak-prognoziruyut-volny-tsunami/",
            headerImageUrl = "https://open.phys.msu.ru/upload/iblock/4be/08gw440byapokld3seoo2rjtcxopcphk/%D0%92%D0%9E%D0%9B%D0%9D%D0%AB%20%D0%A6%D0%A3%D0%9D%D0%90%D0%9C%D0%98.png",
            publishDate = "21.10.2023 17:00:00",
            type = "Университетские субботы",
            topics = listOf(Topic("0", "студентам"), Topic("0", "университетские субботы")),
            isSaved = false,
            hasBeenViewed = false
        ),
        UserNewsResource(
            id = "2",
            title = "Владимир-Москва познавательное путешествие на Физфак",
            content = "19 мая 2023 года совершилось познавательное путешествие\n" +
                    "\n" +
                    "Школьники из Владимира и Москвы не только посетили музей Физфака и лаборатории, но и совершили несколько открытий...\n" +
                    "\n" +
                    "Эта дорожная карта ученицы средней школы поможет вам понять ценность фундаментальных знаний, которые можно получить в МГУ. ",
            url = "https://open.phys.msu.ru/events/vladimir-moskva-poznavatelnoe-puteshestvie-na-fizfak/",
            headerImageUrl = "https://open.phys.msu.ru/upload/iblock/192/z8uc2ymimmfx1mo1zxsdymqrxn5i56dz/IMG_3516.JPG",
            publishDate = "21.10.2023 17:00:00",
            type = "Экскурсии",
            topics = listOf(Topic("0", "студентам"), Topic("0", "экскурсии")),
            isSaved = false,
            hasBeenViewed = false
        ),
    )
}

