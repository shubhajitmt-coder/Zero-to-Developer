package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.DsaPatternModel
import com.example.data.DsaProgress
import com.example.viewmodel.MainViewModel

@Composable
fun DsaHubScreen(
    viewModel: MainViewModel,
    dsaProgressList: List<DsaProgress>
) {
    val patterns = viewModel.getDsaPatterns()
    val searchQuery by viewModel.searchQuery.collectAsState()
    val selectedCategory by viewModel.selectedDsaCategory.collectAsState()
    val langStrategy = viewModel.getLanguageStrategy()

    var activeLanguageTab by remember { mutableStateOf("C++") } // "C++" or "Java"

    val categories = listOf("All", "Arrays & Strings", "Stacks & Queues", "Linked Lists", "Trees & Graphs", "Heaps & Hash Maps", "Dynamic Programming")

    val filteredPatterns = patterns.filter { pattern ->
        val matchesCategory = if (selectedCategory == "All") true else pattern.category.contains(selectedCategory)
        val matchesSearch = pattern.title.contains(searchQuery, ignoreCase = true) || pattern.coreConcept.contains(searchQuery, ignoreCase = true)
        matchesCategory && matchesSearch
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        contentPadding = PaddingValues(bottom = 32.dp, top = 8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // DSA Language Strategy Card
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("dsa_language_strategy_card"),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Terminal, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Language Strategy for DSA & Interviews",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "PRIMARY DSA LANGUAGE: C++ or Java (Pick ONE and master it!).",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "• C++ Pros: Fast execution in Online Assessments (OAs), STL (vector/map/set) syntax is concise.\n" +
                                "• Java Pros: Clean OOP model, direct alignment with Spring Boot backend, built-in memory safety.\n" +
                                "⚠️ AVOID PYTHON FOR INDIAN DSA OAs: Indian hiring tests (Mettl, HackerRank, CodeSignal) have strict time limits. Python code frequently gets TLE (Time Limit Exceeded) errors on test cases that C++/Java pass easily.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        // Search & Language Switcher
        item {
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { viewModel.setSearchQuery(it) },
                    placeholder = { Text("Search 250+ DSA Patterns (e.g. Sliding Window, BFS)...") },
                    leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                    trailingIcon = {
                        if (searchQuery.isNotEmpty()) {
                            IconButton(onClick = { viewModel.setSearchQuery("") }) {
                                Icon(Icons.Default.Clear, contentDescription = "Clear")
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .testTag("dsa_search_field"),
                    shape = RoundedCornerShape(12.dp)
                )

                // C++ vs Java Code Template Toggle
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Code Template Language:",
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        FilterChip(
                            selected = activeLanguageTab == "C++",
                            onClick = { activeLanguageTab = "C++" },
                            label = { Text("C++ STL") },
                            modifier = Modifier.testTag("cpp_toggle_chip")
                        )
                        FilterChip(
                            selected = activeLanguageTab == "Java",
                            onClick = { activeLanguageTab = "Java" },
                            label = { Text("Java Collections") },
                            modifier = Modifier.testTag("java_toggle_chip")
                        )
                    }
                }
            }
        }

        // Category Filter Chips
        item {
            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                items(categories) { cat ->
                    FilterChip(
                        selected = selectedCategory == cat,
                        onClick = { viewModel.selectDsaCategory(cat) },
                        label = { Text(cat) },
                        modifier = Modifier.testTag("dsa_cat_chip_$cat")
                    )
                }
            }
        }

        // Pattern Cards List
        items(filteredPatterns) { pattern ->
            val progress = dsaProgressList.find { it.patternId == pattern.id }
            val solvedCount = progress?.solvedCount ?: 0
            val targetCount = pattern.keyProblemsCount

            DsaPatternCard(
                pattern = pattern,
                solvedCount = solvedCount,
                targetCount = targetCount,
                codeLanguage = activeLanguageTab,
                onSolvedChange = { newCount ->
                    viewModel.updateDsaSolvedCount(pattern.id, newCount, targetCount)
                }
            )
        }
    }
}

@Composable
fun DsaPatternCard(
    pattern: DsaPatternModel,
    solvedCount: Int,
    targetCount: Int,
    codeLanguage: String,
    onSolvedChange: (Int) -> Unit
) {
    var isExpanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .testTag("dsa_pattern_card_${pattern.id}"),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Header
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = pattern.category,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = pattern.title,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                AssistChip(
                    onClick = { },
                    label = { Text(pattern.interviewWeight) },
                    colors = AssistChipDefaults.assistChipColors(
                        containerColor = if (pattern.interviewWeight.contains("Critical")) Color(0xFFEF4444).copy(alpha = 0.15f) else MaterialTheme.colorScheme.surfaceVariant
                    )
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = pattern.coreConcept,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Progress Slider
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Solved: $solvedCount / $targetCount Problems",
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    color = if (solvedCount >= targetCount) Color(0xFF10B981) else MaterialTheme.colorScheme.onSurface
                )

                Row {
                    IconButton(
                        onClick = { if (solvedCount > 0) onSolvedChange(solvedCount - 1) },
                        modifier = Modifier.size(28.dp)
                    ) {
                        Icon(Icons.Default.Remove, contentDescription = "Decrease")
                    }
                    IconButton(
                        onClick = { onSolvedChange(solvedCount + 1) },
                        modifier = Modifier.size(28.dp)
                    ) {
                        Icon(Icons.Default.Add, contentDescription = "Increase")
                    }
                }
            }

            LinearProgressIndicator(
                progress = { (solvedCount.toFloat() / targetCount.toFloat()).coerceIn(0f, 1f) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(6.dp),
                color = if (solvedCount >= targetCount) Color(0xFF10B981) else MaterialTheme.colorScheme.primary,
                trackColor = MaterialTheme.colorScheme.surfaceVariant
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Expand Toggle Button for Code Template
            TextButton(
                onClick = { isExpanded = !isExpanded },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text(if (isExpanded) "Hide Pattern Code Template" else "Show $codeLanguage Code Template")
                Icon(
                    imageVector = if (isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                    contentDescription = null
                )
            }

            AnimatedVisibility(visible = isExpanded) {
                Column(modifier = Modifier.padding(top = 8.dp)) {
                    Surface(
                        shape = RoundedCornerShape(10.dp),
                        color = Color(0xFF0F172A),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = if (codeLanguage == "C++") pattern.cppTemplate else pattern.javaTemplate,
                            style = MaterialTheme.typography.bodySmall,
                            fontFamily = FontFamily.Monospace,
                            color = Color(0xFF38BDF8),
                            fontSize = 12.sp,
                            modifier = Modifier.padding(12.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Common Mistakes to Avoid:",
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFEF4444)
                    )

                    pattern.commonMistakes.forEach { mistake ->
                        Text(
                            text = "• $mistake",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            }
        }
    }
}
