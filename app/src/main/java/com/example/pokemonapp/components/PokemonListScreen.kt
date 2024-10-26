package com.example.pokemonapp.components
//==================================================================================================
import PokemonViewModel
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.pokemonapp.models.Pokemon

//==================================================================================================

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
//Implementation of pokemonViewModel and grabbing the pokemonDetails from said model
fun PokemonListScreen(pokemonViewModel: PokemonViewModel = viewModel()) {
    var pokemonName by remember { mutableStateOf("") }
    val pokemonDetails by pokemonViewModel.pokemon.collectAsState()
//==================================================================================================
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        //Pokemon image from the internet
                        val titleImageUrl = "https://th.bing.com/th/id/OIP.V4gGLQev-LVw0FmHirI__gHaD4?rs=1&pid=ImgDetMain"

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            AsyncImage(
                                model = titleImageUrl,
                                contentDescription = "Pokémon Image",
                                modifier = Modifier
                                    .size(150.dp)
                                    .padding(end = 8.dp)
                            )

                        }
                    }
                }
            )
        }
        //==================================================================================================
    ) { padding ->
        //This us for the textbox to Arrange at the top with padding
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(10.dp),
            verticalArrangement = Arrangement.Top
        ) {
            //Grabs the pokemonName String
            OutlinedTextField(
                value = pokemonName,
                onValueChange = { pokemonName = it },
                label = { Text("Enter Pokémon Name") },
                modifier = Modifier.fillMaxWidth()

            )

            Spacer(modifier = Modifier.height(8.dp))

            //When clicked it will fetch the pokemon details from the pokemonViewModel
            Button(
                onClick = { pokemonViewModel.fetchPokemonDetailsByName(pokemonName) },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("Go")
            }


            // Display Pokémon Details using LazyColumn
            LazyColumn(
                contentPadding = PaddingValues(vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(pokemonDetails) { pokemon ->
                    PokemonItem(pokemon)
                }
            }
        }
    }
}
//==================================================================================================

@Composable
fun PokemonItem(pokemon: Pokemon) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Load the Pokémon image
            AsyncImage(
                model = pokemon.sprites.front_default,
                contentDescription = "${pokemon.name} image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Display Pokémon details centered
            Text(text = "Name: ${pokemon.name}")
            Text(text = "Types: ${pokemon.types.joinToString { it.type.name }}")
            Text(text = "Height: ${pokemon.height}")
            Text(text = "Weight: ${pokemon.weight}")
        }
    }
}
//==================================================================================================
