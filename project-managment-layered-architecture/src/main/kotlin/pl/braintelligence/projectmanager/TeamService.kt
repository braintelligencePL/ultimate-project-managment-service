package pl.braintelligence.projectmanager

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import pl.braintelligence.projectmanager.dto.ExistingTeamDto
import pl.braintelligence.projectmanager.dto.NewTeamDto
import pl.braintelligence.projectmanager.domain.team.Team
import pl.braintelligence.projectmanager.domain.team.TeamRepository
import pl.braintelligence.projectmanager.domain.exceptions.TeamAlreadyExistException
import java.lang.invoke.MethodHandles

@Service
class TeamService(
        val teamRepository: TeamRepository
) {
    fun createTeam(newTeamDto: NewTeamDto) {
        logger.info("Creating new team {}.", newTeamDto)

        when (teamRepository.existByName(newTeamDto.name)) {
            true -> {
                logger.warn("Team {} already exist.", newTeamDto)
                throw TeamAlreadyExistException()
            }
            false -> {
                teamRepository.save(Team(newTeamDto.name))
            }
        }
    }

    fun getTeams(): List<ExistingTeamDto> {
        val teams = teamRepository.findAll()

        return ExistingTeamDto.mapToExistingTeams(teams)
    }

    companion object {
        private val logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass())
    }

}