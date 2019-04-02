package pl.braintelligence.projectmanager.infrastructure.adapter.incoming.rest

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import pl.braintelligence.projectmanager.core.projects.domain.Project
import pl.braintelligence.projectmanager.core.projects.ports.incoming.ProjectCreator
import pl.braintelligence.projectmanager.infrastructure.adapter.incoming.rest.dto.ProjectDraft

/**
 * Primary Adapter
 */

@RestController
@RequestMapping("/projects")
internal class ProjectController(
        private val projectCreator: ProjectCreator
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createProjectDraft(
            @RequestBody projectDraft: ProjectDraft
    ): Project = projectCreator.createProjectDraft(projectDraft)

//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    fun createProjectDraft(
//            @RequestBody projectDraft: ProjectDraft
//    ): Project = projectCreator.createProjectDraft(projectDraft)


}
