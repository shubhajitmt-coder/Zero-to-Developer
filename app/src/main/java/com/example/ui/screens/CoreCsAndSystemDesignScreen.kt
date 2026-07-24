package com.example.ui.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.data.CoreSubjectModel
import com.example.viewmodel.MainViewModel

@Composable
fun CoreCsAndSystemDesignScreen(viewModel: MainViewModel) {
    val coreSubjects = viewModel.getCoreSubjects()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        contentPadding = PaddingValues(bottom = 32.dp, top = 8.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // System Design Timing & Foundation Banner
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("system_design_timing_card"),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
            ) {
                Column(modifier = Modifier.padding(18.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(Icons.Default.Architecture, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(
                            text = "System Design Strategy: WHEN to Start?",
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "⚠️ DO NOT start System Design too early! Studying high-level architecture before building basic REST APIs and database schemas is useless.",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "• START TIMELINE: Month 14 (Year 2 Quarter 5) — AFTER you have built REST APIs, connected PostgreSQL databases, used JWT Auth, and containerized apps with Docker.\n" +
                                "• System Design Path: 1) Client-Server & HTTP -> 2) DB Indexing & Scaling -> 3) Load Balancers & Consistent Hashing -> 4) Redis Caching -> 5) Message Queues (Kafka) -> 6) LLD & Machine Coding.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }
        }

        // Section Title: Core CS Subjects
        item {
            Text(
                text = "Core CS: Exam vs Interview vs Real Engineering",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
        }

        // Core Subject Cards
        items(coreSubjects) { subject ->
            CoreSubjectCard(subject = subject)
        }
    }
}

@Composable
fun CoreSubjectCard(subject: CoreSubjectModel) {
    var isExpanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .testTag("core_cs_card_${subject.subject.take(10)}"),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = subject.subject,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.weight(1f)
                )

                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color = MaterialTheme.colorScheme.tertiaryContainer
                ) {
                    Text(
                        text = subject.bscOverlapPercentage,
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onTertiaryContainer,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Key Topics Chips
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                subject.keyTopics.take(3).forEach { topic ->
                    AssistChip(
                        onClick = { },
                        label = { Text(topic, style = MaterialTheme.typography.labelSmall) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // 3-Way Comparison Box
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                UsageBox(title = "📚 College Exam Focus:", text = subject.examFocus, badgeColor = MaterialTheme.colorScheme.secondaryContainer)
                UsageBox(title = "💼 SDE Interview Focus:", text = subject.interviewFocus, badgeColor = MaterialTheme.colorScheme.primaryContainer)
                UsageBox(title = "🛠️ Real Software Dev Usage:", text = subject.realDevUsage, badgeColor = MaterialTheme.colorScheme.surfaceVariant)
            }
        }
    }
}

@Composable
fun UsageBox(title: String, text: String, badgeColor: Color) {
    Surface(
        shape = RoundedCornerShape(10.dp),
        color = badgeColor.copy(alpha = 0.5f),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Text(
                text = title,
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = text,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}
