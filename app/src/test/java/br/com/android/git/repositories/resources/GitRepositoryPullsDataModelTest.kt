package br.com.android.git.repositories.resources

import br.com.android.commons.data.models.*

object GitRepositoryPullsDataModelTest {

    val gitRepositoryDataModel = GitRepositoryPullsModel(
        id = 1L,
        body = "Body",
        title = "Title",
        number = 1L,
        pullURL = "Pull URL",
        createdAt = "Created At",
        base = GitRepositoryBaseModel(
            repository = GitRepositoryDataModel(
                id = 1L,
                name = "Name",
                fullName = "Full Name",
                forksCount = 10000L,
                description = "Description",
                watchersCount = 5000L,
                owner = GitRepositoryOwnerModel(
                    id = 1L,
                    login = "User name",
                    avatarUrl = "Avatar URL",
                )
            )
        ),
        user = GitRepositoryOwnerModel(
            id = 1L,
            login = "User name",
            avatarUrl = "Avatar URL",
        ),
    )

    val gitRepositoryPullsDataModelList = arrayListOf<GitRepositoryPullsModel>().apply {
        (0..5).forEach { add(gitRepositoryDataModel.copy(id = it.toLong())) }
    }
}