package io.andakawa.bot.commands.utils

import io.andakawa.bot.Settings
import io.andakawa.bot.commands.Command
import io.andakawa.bot.mal.GetAnimeList
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent
import java.lang.StringBuilder

class SearchAnime : Command("${Settings.PREFIX}searchAnime") {
    override fun run(event: GuildMessageReceivedEvent) {
        val animeList = GetAnimeList()
        val request = event.message.contentRaw.split(" ").toMutableList()
        request.removeAt(0)
        val search = request.joinToString(" ")
        val message = StringBuilder();
        for ( anime in animeList.searchForAnime(search)!!){
            message.append(anime.title).append("\n")
        }
        event.channel.sendMessage(message).queue()
    }
}
