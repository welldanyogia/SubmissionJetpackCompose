package com.example.submissionjetpackcompose.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.submissionjetpackcompose.UiState
import com.example.submissionjetpackcompose.ViewModelFactory
import com.example.submissionjetpackcompose.data.di.Injection
import com.example.submissionjetpackcompose.data.model.HeroesList
import com.example.submissionjetpackcompose.ui.components.Item
import com.example.submissionjetpackcompose.ui.components.Search

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateToDetail: (Long) -> Unit,
){
    val query by viewModel.query
    Box(modifier = modifier.fillMaxSize()) {
        Column {
            Search(
                query = query,
                onQueryChange = viewModel::search,
                modifier = Modifier,
            )
            viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
                when (uiState) {
                    is UiState.Loading -> {
                        viewModel.getAllHeroes()
                    }

                    is UiState.Success -> {
                        HeroList(
                            heroesList = uiState.data,
                            modifier = modifier,
                            navigateToDetail = navigateToDetail,
                        )
                    }

                    is UiState.Error -> {}
                }
            }
        }
    }
}


@Composable
fun HeroList(
    heroesList: List<HeroesList>,
    modifier: Modifier = Modifier,
    navigateToDetail: (Long) -> Unit,
){
    Box(modifier = modifier){
        LazyColumn{
            items(heroesList){ data ->
                Item(
                    photoUrl = data.heroesList.photoUrl,
                    name = data.heroesList.name,
                    modifier = Modifier
                        .clickable {
                        navigateToDetail(data.heroesList.id)
                    }
                        .fillMaxWidth()
                        .padding(16.dp)
                    )
            }
        }
    }
}



