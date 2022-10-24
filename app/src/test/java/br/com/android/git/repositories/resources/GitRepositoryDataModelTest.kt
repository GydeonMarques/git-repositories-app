package br.com.android.git.repositories.resources

import br.com.android.commons.data.models.GitRepositoryDataModel
import br.com.android.commons.data.models.GitRepositoryOwnerModel

object GitRepositoryDataModelTest {


    val gitRepositoryDataModel = GitRepositoryDataModel(
        id = 1L,
        name = "Name",
        fullName = "Full name",
        forksCount = 100L,
        description = "Description",
        watchersCount = 500L,
        owner = GitRepositoryOwnerModel(
            id = 1L,
            login = "User name",
            avatarUrl = "Avatar URL",
        )
    )

    val gitRepositoryDataModelList = arrayListOf<GitRepositoryDataModel>().apply {
        (0..10).forEach { add(gitRepositoryDataModel.copy(id = it.toLong())) }
    }
}